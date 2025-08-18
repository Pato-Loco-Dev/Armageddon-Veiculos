package br.com.armageddon.veiculos.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "VEICULOS_A_VENDA")
public class Veiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_VEICULO")
    private Long id;

    @NotNull
    @Column(name = "FABRICANTE", length =30)
    private String fabricante;

    @NotNull
    @Column(name = "MODELO", length = 50)
    private String modelo;

    @Column(name = "ANO_FAB", length = 4)
    private String anoFab;

    @Column(name = "MOTOR", length = 30)
    private String motor;

    @Column(name = "LITRAGEM", length = 10)
    private String litragem;

    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "FOTO")
    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(String anoFab) {
        this.anoFab = anoFab;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getLitragem() {
        return litragem;
    }

    public void setLitragem(String litragem) {
        this.litragem = litragem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
