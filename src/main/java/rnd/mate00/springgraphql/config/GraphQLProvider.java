package rnd.mate00.springgraphql.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Value("classpath:graphql/schema.graphqls")
    private Resource schemaFile;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        File file = schemaFile.getFile();
        String content = new String(Files.readAllBytes(file.toPath()));

        GraphQLSchema schema = buildSchema(file);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private GraphQLSchema buildSchema(File schemaContent) {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaContent);
        RuntimeWiring runtimeWiring = buildWiring();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("bookById", bookDataFetcher.getBookByIdDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("allBooks", bookDataFetcher.allBooks()))
                .build();
    }
}
