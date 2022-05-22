To build and run project:<br/>
1- First install and run docker and docker composer.<br/>
2- Open a bash command prompt and run following commands:<br/>
    $ cd /[path to workOrder dir]/<br/>
    $ mvn clean package<br/>
    $ docker image build . -t work-order-project<br/>
    $ docker-compose up<br/>
3- Open another bash command prompt and run following command:<br/>
    $ docker restart work-order-project<br/>
4- After that work-order-project container restarted in first command prompt<br/>
    you can access web UI in http://localhost:8080/work-order/validation-history <br/>
    and rest-controller apis in the following addresses:<br/>
        - http://localhost:8080/work-order/api/work-order/validation as post method to validate json data.<br/>
        - http://localhost:8080/work-order/api/validation-history/getall as get method to view all validation history.<br/>


