# Khi mới cài ubuntu khuyến khích nên để username: hauvan thì khỏi sửa path ở local_settings.py :))
# Các bước setup
`Khi nó yêu cầu quyền thì chứ đặt sudo trước các câu lệnh là okela`
1. Cài đặt những cái cần thiết khi mới cài linux
```
$ apt update
$ apt install git gcc g++ make python3-dev python3-pip python3-venv libxml2-dev libxslt1-dev zlib1g-dev gettext curl redis-server pkg-config
$ curl -sL https://deb.nodesource.com/setup_18.x | sudo -E bash -
$ apt install nodejs
```

2. Tạo cơ sở dữ liệu ở đây xài MariaDB
```
$ apt update
$ apt install mariadb-server libmysqlclient-dev

$ sudo mysql
mariadb> CREATE DATABASE moodle DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
mariadb> GRANT ALL PRIVILEGES ON moodle.* TO 'moodle'@'localhost' IDENTIFIED BY '<mariadb user password>';
mariadb> exit
```
3. Chạy virtutal enviroment
```
. moodlesite/bin/activate
```
lúc active thì nó sẽ hiện `(moodlesite)`

4. Cài code
```
(vnojsite) $ git clone --recursive https://github.com/hauvanvn/WebTest.git WebProject
(vnojsite) $ cd WebProject/site
```
5. Đọc tiếp link này bắt đầu từ mục **Installing prerequisites/Install Python dependencies into the virtual environment.** https://vnoi-admin.github.io/vnoj-docs/#/site/installation

## Note:
- Khi vừa clone git về xong, vô `WebTest/site/dmoj/local_settings.py` để sửa lại path như sau:\
  Sửa lại path `/home/hauvan/WebPoject/...` -> `/home/<username linux>/WebTest/...` \
  ![image](https://github.com/hauvanvn/WebTest/assets/83352342/46a8b215-3324-4ac9-aa7c-4e1bd4a350eb)
- Khi setup Judge(máy chấm): [link cách cài máy chấm](https://vnoi-admin.github.io/vnoj-docs/#/judge/setting_up_a_judge)
  - Tạo judge ở đường link http://localhost:8000/admin/judge/judge/ khi chạy web
    ![image](https://github.com/hauvanvn/WebTest/assets/83352342/aee51a73-4110-4ef9-8cca-2cab2dc6ef40)
  - Hãy copy <Judge name> và <Authentication key> vì mình sẽ cần dùng\
    ![image](https://github.com/hauvanvn/WebTest/assets/83352342/1e2a9194-45b2-44cd-8446-b88727a4f6b3)
    và bấm Save\
    ![image](https://github.com/hauvanvn/WebTest/assets/83352342/2a83672e-0572-4141-8b74-8f01d632dfe0)
  - Mở terminal mới và nhập lệnh
    ```
    cd WebTest/database/problems
    ```
  - Nhập lệnh
    ```
    sudo nano <Judge name>.yml
    ```
    rồi cop đoạn mã này: **Nhớ đổi <judge name> và <judge authentication key> thành cái mình đã cop**
    ```
    id: <judge name>
    key: <judge authentication key>
    problem_storage_globs:
      - /problems/*
    ```
    xong rùi 'ctrl + x' -> `y` -> `Enter`
  - Tiếp tục nhập lệnh
    ```
    sudo docker run \
    --name <judge name> \
    --network="host" \
    -v /home/<username linux>/WebTest/database/problems:/problems \
    --cap-add=SYS_PTRACE \
    -d \
    --restart=always \
    vnoj/judge-tiervnoj:latest \
    run -p 9999 -c /problems/judge.yml localhost -A 0.0.0.0 -a 12345
    ```
    Nhớ chỉnh port 12345 thành những mã khác đối với các máy chấm khác nhau\
    Nó sẽ cài đặt bộ dịch nếu chưa cài bộ dịch và sẽ ra đoạn mã thì thành công.
  - Nếu nó ghi chưa cài docker thì chạy lệnh lại và chạy lại lệnh trên:
    ```
    sudo apt install docker.io
    ```
  - Check máy chấm chạy đc không bằng lệnh
    ```
    sudo docker logs -ft <judge name>
    ```
    **Lưu ý:**
    - phải có project có bộ test mới chạy được lệnh này. Chi tiết cách tạo problems liên hệ Kiệt hoặc Hậu
    - nếu web chưa set [uWSGI](https://vnoi-admin.github.io/vnoj-docs/#/site/installation?id=setting-up-uwsgi) thì phải chạy
    ```
    (moodlesite) $ ./manage.py runbridged
    ```
    ở 1 terminal khác
  - 1 Số lệnh docker:
    - `sudo docker stop <judge name>` để dừng 1 docker
    - `sudo docker rm <judge name>` để xóa 1 docker
