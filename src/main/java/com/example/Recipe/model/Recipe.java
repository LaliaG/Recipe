package com.example.Recipe.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private UUID id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
   // @NotBlank
   // @Size(min = 1, message = "Il doit y avoir au moins un ingrédient")
    private List<String> ingredients;

    @NotBlank
    @NotNull
    private String instructions;

    private Category category;

    @NotNull
    @NotBlank
    private String categoryId;
}
