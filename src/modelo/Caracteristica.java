package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Caracteristica {

    private Caracteristica caracteristicaPai;
    private List<Caracteristica> caracteristicasFilhas;
    private List<Animal> animais;
    private String nome;

    public Caracteristica(String nome) {
        this.nome = nome;
        animais = new ArrayList<>();
        caracteristicasFilhas = new ArrayList<>();
    }

    public Caracteristica(String nome, Caracteristica caracteristicaPai) {
        this(nome);
        this.caracteristicaPai = caracteristicaPai;
    }

    public Caracteristica getCaracteristicaPai() {
        return caracteristicaPai;
    }

    public void setCaracteristicaPai(Caracteristica caracteristicaPai) {
        if (this.caracteristicaPai != null && !this.caracteristicaPai.equals(caracteristicaPai)) {
            this.caracteristicaPai.getCaracteristicasFilhas().remove(this);
        }
        caracteristicaPai.getCaracteristicasFilhas().add(this);
        this.caracteristicaPai = caracteristicaPai;
    }

    public List<Caracteristica> getCaracteristicasFilhas() {
        return caracteristicasFilhas;
    }

    public void setCaracteristicasFilhas(List<Caracteristica> caracteristicasFilhas) {
        this.caracteristicasFilhas = caracteristicasFilhas;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return animais.equals(that.animais) &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animais, nome);
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "animais=" + animais +
                ", nome='" + nome + '\'' +
                '}';
    }
}
