**Feature API**

This is a basic RestAPI monolith project, which has some basic operations for features.

While starting the application, you have to use DemoApplication class. This is the configuration class from Spring boot.

There is another configuration file, which name is DemoSecurityConfig class. This is for configure some Spring Security standarts.
 - WebSecurity configure method is for reaching to h2-console without spring authorization.
 - HttpSecurity configure method is for reaching to "get-feature" method by everyone.

After starting the application, you can call these endpoints;
 - localhost:8081/create-feature (POST)
 - localhost:8081/update-feature (PUT)
 - localhost:8081/get-features (GET)

Because of the security requests, First two service require an authorization. You can see the credentials in the application.properties.
"get-features" service is by-passed from the security with Security configuration class. Because everyone should reach to the service.

You can find a file in resources, that name is data.sql. It helps for insert some user while compiling.
You can see the entities. In the mean time, there is a DTO class for keeping the relationship between Feature and User tables. 

You can find a test class in test package. I wrote one class for Controller and Service, because I covered all lines for these two classes.

You can find detailed information about each class and method in the java doc lines in the related classes.