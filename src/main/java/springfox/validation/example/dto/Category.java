/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package springfox.validation.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import springfox.validation.example.dto.interfaces.response.DELETE;
import springfox.validation.example.dto.interfaces.response.INFO;
import springfox.validation.example.dto.interfaces.request.PATCH;
import springfox.validation.example.dto.interfaces.request.POST;

import javax.validation.constraints.NotNull;

/**
 * Category
 */
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {

  @JsonView(DELETE.class)
  @NotNull(groups = {INFO.class, DELETE.class})
  @ApiModelProperty(value = "ID", example = "1")
  private Long id;

  @JsonView({INFO.class, POST.class, PATCH.class})
  @NotNull(groups = {INFO.class, POST.class})
  @ApiModelProperty(value = "Name", example = "Category#1")
  private String name;

  @JsonView({INFO.class, POST.class, PATCH.class})
  @NotNull(groups = {INFO.class, POST.class})
  @ApiModelProperty(value = "Child")
  private Child child;

  public Category(Long id) {
    this.id = id;
  }
}

