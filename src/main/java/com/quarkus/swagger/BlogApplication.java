package com.quarkus.swagger;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "Blog API", version = "1.0.0",
        contact = @Contact(name = "Valieva Elina - developer",
                url = "https://github.com/ElinaValieva",
                email = "Elina.Valieva@t-systems.com"),
        license = @License(
                name = "Apache 2.0",
                url = "http://www.apache.org/licenses/LICENSE-2.0.html")))
public class BlogApplication extends Application {
}
