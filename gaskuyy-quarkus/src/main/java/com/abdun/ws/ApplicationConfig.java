package com.abdun.ws;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
    tags = {
            @Tag(name="Java", description="The Language used is Java."),
            @Tag(name="Quarkus", description="The Framework used is Quarkus.")
    },
    info = @Info(
        title="Assessment API",
        version = "0.1",
        contact = @Contact(
            name = "Panemu",
            url = "https://panemu.com",
            email = "info@panemu.com"),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)

@jakarta.ws.rs.ApplicationPath("assessment/api")
public class ApplicationConfig extends Application {
}
