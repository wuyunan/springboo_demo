package com.qingteng.demo.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.qingteng.demo.entity.JwtUser;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

import static java.util.Collections.singleton;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CommonApiListScannerPlugin implements ApiListingScannerPlugin {

    private final CachingOperationNameGenerator operationNames;

    /**
     * @param operationNames - CachingOperationNameGenerator is a component bean
     *                       that is available to be autowired
     */
    public CommonApiListScannerPlugin(CachingOperationNameGenerator operationNames) {
        this.operationNames = operationNames;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Multimap<String, Example> examples = ArrayListMultimap.create();
        examples.put(APPLICATION_JSON_VALUE, new Example("{'username': 'admin', 'password':'123'}"));
        examples.put(APPLICATION_FORM_URLENCODED_VALUE, new Example("username=admin&password=123"));
        return new ArrayList<>(
                Arrays.asList(
                        new ApiDescription(
                                "rest-api",
                                "/login",
                                "用户登录入口",
                                ImmutableList.of(
                                        new OperationBuilder(operationNames)
                                                .authorizations(new ArrayList<>())
                                                .codegenMethodNameStem("loginPOST")
                                                .method(HttpMethod.POST)
                                                .consumes(new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE)))
                                                .notes("用户登录入口，所有角色的用户都通过该入口登录")
                                                .parameters(
                                                        Collections.singletonList(
                                                                new ParameterBuilder()
                                                                        .description("登录相关信息")
                                                                        .type(new TypeResolver().resolve(JwtUser.class))
                                                                        .name("credential")
                                                                        .parameterType("body")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("UserCredential"))
                                                                        .complexExamples(examples)
                                                                        .build()
                                                        )
                                                )
                                                .responseMessages(responseMessages())
                                                .responseModel(new ModelRef("string"))
                                                .build()
                                ),
                                false
                        )
                )
        );
    }

    /**
     * @return Set of response messages that overide the default/global response messages
     */
    private Set<ResponseMessage> responseMessages() { //<8>
        return singleton(new ResponseMessageBuilder()
                .code(200)
                .message("登陆成功")
                .responseModel(new ModelRef("AuthenticationResponse"))
                .build());
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
