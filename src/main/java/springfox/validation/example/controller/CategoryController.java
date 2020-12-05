package springfox.validation.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.validation.example.dto.Category;
import springfox.validation.example.dto.interfaces.response.DELETE;
import springfox.validation.example.dto.interfaces.response.INFO;
import springfox.validation.example.dto.interfaces.request.PATCH;
import springfox.validation.example.dto.interfaces.request.POST;

import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * See <a href="http://localhost:8094/swagger-ui/index.html#/">Swagger UI</a>
 */
@RestController
@RequestMapping(value = "/v1")
public class CategoryController {

    private final AtomicLong identity = new AtomicLong(1);
    private final Map<Long, Category> categories = new ConcurrentHashMap<>();

    @Validated(INFO.class)
    @JsonView(INFO.class)
    @ApiOperation(value = "Add category")
    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Category post(@RequestBody @JsonView(POST.class) @Validated(POST.class) Category category) {
        long categoryId = identity.getAndIncrement();
        category.setId(categoryId);
        categories.put(categoryId, category);
        return category;
    }

    @Validated(INFO.class)
    @JsonView(INFO.class)
    @ApiOperation(value = "Patch category")
    @PatchMapping(value = "/category/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    public Category patch(@RequestBody @JsonView(PATCH.class) @Validated(PATCH.class) Category category,
                          @PathVariable @Min(1) long categoryId) {

        Category item = getEntityOrThrowException(categoryId);
        Optional.ofNullable(category.getName()).ifPresent(item::setName);
        return item;
    }

    @Validated(INFO.class)
    @JsonView(INFO.class)
    @ApiOperation(value = "Put category")
    @PutMapping(value = "/category/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Category put(@RequestBody @JsonView(POST.class) @Validated(POST.class) Category category,
                        @PathVariable @Min(1) long categoryId) {

        Category item = getEntityOrThrowException(categoryId);
        item.setName(category.getName());
        return item;
    }

    @Validated(INFO.class)
    @JsonView(INFO.class)
    @ApiOperation(value = "Get category")
    @GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category get(@PathVariable @Min(1) long categoryId) {
        return getEntityOrThrowException(categoryId);
    }

    @JsonView(DELETE.class)
    @Validated(DELETE.class)
    @ApiOperation(value = "Delete category")
    @DeleteMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category delete(@PathVariable @Min(1) long categoryId) {
        categories.put(categoryId, null);
        return new Category(categoryId);
    }

    @Validated(INFO.class)
    @JsonView(INFO.class)
    @ApiOperation(value = "Get categories")
    @GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Category> get() {
        return categories.values();
    }

    private Category getEntityOrThrowException(long categoryId) {
        return Optional.ofNullable(categories.get(categoryId)).orElseThrow(() -> {
            throw new RuntimeException("Entity is not found");
        });
    }
}
