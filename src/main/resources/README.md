**Feature API**

This is a basic RestAPI monolith project, which has some basic operations for features.

While starting the application, you have to use DemoApplication class. This is the configuration class from Spring boot.

There is another configuration file, which name is DemoSecurityConfig class. This is for configure some Spring Security standarts.
 - WebSecurity configure method is for reaching to h2-console without spring authorization.
 - HttpSecurity configure method shows which roles can reach which services.
 - AuthenticationManagerBuilder method for enable my custumized AuthenticationProvider.

After starting the application, you can call these endpoints;
 - localhost:8081/feature/create (POST)
 - localhost:8081/feature/update (PUT)
 - localhost:8081/feature/get (GET)

Because of the security requests, users and admin have specific role in Authorities table. You should basic authentication for reaching out the services.
Credentials are in data.sql file.

Below auth package, you can see two classes. CustomAuthenticationProvider provides us authentication with username and password.
The other class, which name is SpringContext, provides us add the UserRepository to the context for calling from Provider class.  

You can find a file in resources, that name is data.sql. It helps for insert some users and authorities while compiling.
You can see the entities. In the mean time, there is a DTO class for keeping the relationship between Feature and User tables. 

You can find two test classes in test package.

You can find detailed information about each class and method in the java doc lines in the related classes.