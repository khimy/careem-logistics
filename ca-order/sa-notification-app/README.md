docker run --name=mysql -p 3306:3306 -e "MYSQL_ROOT_PASSWORD=root-password" -e "MYSQL_USER=mysql" -e "MYSQL_PASSWORD=mysql" -e "MYSQL_DATABASE=accounting" -d  mysql:5.7.13
