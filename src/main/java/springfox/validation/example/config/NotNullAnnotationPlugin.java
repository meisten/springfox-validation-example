package springfox.validation.example.config;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Order
@Component
public class NotNullAnnotationPlugin extends springfox.bean.validators.plugins.schema.NotNullAnnotationPlugin {

}