package springfox.validation.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import springfox.validation.example.dto.interfaces.response.INFO;
import springfox.validation.example.dto.interfaces.request.PATCH;
import springfox.validation.example.dto.interfaces.request.POST;

import javax.validation.constraints.NotNull;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Child {

  @JsonView({INFO.class, POST.class, PATCH.class})
  @NotNull(groups = {INFO.class, POST.class})
  @ApiModelProperty(value = "Name", example = "Child#1")
  protected String name;

}

