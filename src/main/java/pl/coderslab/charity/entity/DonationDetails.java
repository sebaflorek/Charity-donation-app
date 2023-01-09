package pl.coderslab.charity.entity;

import lombok.Data;

@Data
public class DonationDetails {
    private Long id;
    private String pickedUpDate; // Data odebrania datku
    private String deliveryDate; // Data dostarczenia datku do Instytucji [opcjonalnie]
    private String status; // Zrobić obiekt dla statusu (np. Enum: nieodebrana/odebrana/przekazana[opcjonalnie])
}
