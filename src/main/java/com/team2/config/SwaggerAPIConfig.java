package com.team2.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {

    @Value("${readedu.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("sgradosa2@upao.edu.pe");
        contact.setName("Samil Grados");
        contact.setUrl("https://github.com/ReadEDU/readedu-api");

        License mitLicense = new License().name("MIT").url("https://opensource.org/licenses/MIT");

        //Informacion general de la API
        Info info = new Info()
                .title("Readedu API")
                .version("1.0")
                .contact(contact)
                .description("Readedu API")
                .termsOfService("https://github.com/ReadEDU/readedu-api")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);

    }
}
