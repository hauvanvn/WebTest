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
