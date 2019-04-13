package modelo;

import controle.ControleCaracteristicas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private ControleCaracteristicas controleCaracteristicas;

    public Jogo() {
        this.reiniciar();
    }

    private Animal qualAnimalUsuarioPensou(Animal animalAtual, Caracteristica... caracteristica) {
        String nomeAnimal = JOptionPane.showInputDialog(null, "Qual animal você pensou?");
        if (nomeAnimal == null || nomeAnimal.isEmpty()) {
            finalizarJogo();
        }
        String acaoAnimal = JOptionPane.showInputDialog(null, "um(a) " + nomeAnimal + " _____ mas um(a) " + animalAtual.getNome() + " não");
        if (acaoAnimal == null || acaoAnimal.isEmpty()) {
            finalizarJogo();
        }
        Animal animal = new Animal(nomeAnimal);
        if (caracteristica != null && caracteristica.length > 0) {
            animal.addCaracteristicas(caracteristica);
            controleCaracteristicas.getCaracteristicaByName(acaoAnimal).setCaracteristicaPai(caracteristica[0]);
        }
        animal.addCaracteristicas(controleCaracteristicas.getCaracteristicaByName(acaoAnimal));
        return animal;
    }

    public void jogar() {
        JOptionPane.showMessageDialog(null, "Bem vindo ao jogo dos animais, esse aplicativo faz parte do processo seletivo da MGA Gestão Pública. Vamos Começar?", "Jogo dos Animais", JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            JOptionPane.showMessageDialog(null, "Pense em um animal!", "Jogo dos Animais", JOptionPane.INFORMATION_MESSAGE);
            List<Caracteristica> caracteristicasDaRodada = controleCaracteristicas.caracteristicasSorteadas();
            percorrerCaracteristicas(caracteristicasDaRodada);
        }
    }

    private void percorrerCaracteristicas(List<Caracteristica> caracteristicas) {
        for (Caracteristica caracteristica : new ArrayList<>(caracteristicas)) {
            int opt = JOptionPane.showConfirmDialog(null, "O animal que você pensou " + caracteristica.getNome() + "?", "Jogo dos Animais", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                for (Animal animal : new ArrayList<>(caracteristica.getAnimais())) {
                    opt = JOptionPane.showConfirmDialog(null, "O animal que você pensou é um " + animal.getNome() + "?", "Jogo dos Animais", JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Acertei", "Jogo dos Animais", JOptionPane.INFORMATION_MESSAGE);
                        opt = JOptionPane.showConfirmDialog(null, "Quer continuar jogando?", "Jogo dos Animais", JOptionPane.YES_NO_OPTION);
                        if (opt != JOptionPane.YES_OPTION) {
                            finalizarJogo();
                        }
                    } else if (caracteristica.getAnimais().indexOf(animal) == caracteristica.getAnimais().size() - 1) {
                        if (caracteristica.getCaracteristicasFilhas().size() == 0) {
                            qualAnimalUsuarioPensou(animal, caracteristica);
                        } else {
                            percorrerCaracteristicas(caracteristica.getCaracteristicasFilhas());
                        }
                        return;
                    }
                }
            } else if (caracteristicas.indexOf(caracteristica) == caracteristicas.size() - 1) {
                qualAnimalUsuarioPensou(caracteristica.getAnimais().get(caracteristica.getAnimais().size() - 1));
                return;
            }
        }
    }

    public void finalizarJogo() {
        JOptionPane.showMessageDialog(null, "Obrigado por Jogar!", "Jogo dos Animais", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public void reiniciar() {
        controleCaracteristicas = new ControleCaracteristicas();
        Animal animal = new Animal("Tubarão");
        animal.addCaracteristicas(controleCaracteristicas.getCaracteristicaByName("vive na água"));
    }
}
