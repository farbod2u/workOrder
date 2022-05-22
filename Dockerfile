FROM tomcat:9.0
COPY ./target/work-order.war /usr/local/tomcat/webapps/work-order.war
EXPOSE 8080
CMD ["catalina.sh", "run"]