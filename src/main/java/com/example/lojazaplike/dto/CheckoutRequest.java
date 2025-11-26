package com.example.lojazaplike.dto;

import lombok.Data;

@Data
public class CheckoutRequest {

    private String sessionId;

    // Dados do cliente
    private String name;
    private String phone;

    // Endereço
    private String region;      // Jandira, Barueri, Cotia, Itapevi
    private String cep;
    private String street;
    private String number;
    private String complement;
    private String reference;

    // Pagamento
    private String paymentMethod;  // Dinheiro, Cartão, Pix...
    private Double change;         // Troco (somente se precisar)
}