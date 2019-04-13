package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {

    private String nome;
    private List<Caracteristica> caracteristicas;


    public Animal(String nome) {
        this.nome = nome;
        this.caracteristicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addCaracteristicas(List<Caracteristica> caracteristicas) {
        for (Caracteristica c : caracteristicas) {
            if (!c.getAnimais().contains(this)) {
                c.getAnimais().add(this);
            }
            this.caracteristicas.add(c);
        }
    }

    public void addCaracteristicas(Caracteristica... caracteristicas) {
        for (Caracteristica c : caracteristicas) {
            if (!c.getAnimais().contains(this)) {
                c.getAnimais().add(this);
            }
            this.caracteristicas.add(c);
        }
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return nome.equals(animal.nome) &&
                caracteristicas.equals(animal.caracteristicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, caracteristicas);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
