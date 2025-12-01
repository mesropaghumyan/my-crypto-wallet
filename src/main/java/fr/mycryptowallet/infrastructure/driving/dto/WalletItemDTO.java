package fr.mycryptowallet.infrastructure.driving.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletItemDTO(
        @NotBlank(message = "Le nom de la crypto ne peut pas être vide")
        String crypto,

        @NotNull(message = "La quantité est obligatoire")
        @Min(value = 0, message = "La quantité doit être positive")
        Double quantity
) {}