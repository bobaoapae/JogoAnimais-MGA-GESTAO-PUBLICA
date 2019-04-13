package modelo;

import controle.ControleCaracteristicas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private ControleCaracteristicas controleCaracteristicas;
    private List<Animal> animaisRespondidos;

    public Jogo() {
        this.reiniciar();
    }

    private Animal qualAnimalUsuarioPensou(Animal animalAtual) {
        String nomeAnimal = JOptionPane.showInputDialog(null, "Qual animal você pensou?");
        if (nomeAnimal == null || nomeAnimal.isEmpty()) {
            finalizarJogo();
        }
        String acaoAnimal = JOptionPane.showInputDialog(null, "um(a) " + nomeAnimal + " _____ mas um(a) " + animalAtual.getNome() + " não");
        if (acaoAnimal == null || acaoAnimal.isEmpty()) {
            finalizarJogo();
        }
        Animal animal = new Animal(nomeAnimal);
        animal.addCaracteristicas(animalAtual.getCaracteristicas());
        animal.addCaracteristicas(controleCaracteristicas.getCaracteristicaByName(acaoAnimal));
        return animal;
    }

    public void jogar() {
        JOptionPane.showMessageDialog(null, "Bem vindo ao jogo dos animais, esse aplicativo faz parte do processo seletivo da MGA Gestão Pública. Vamos Começar?", "Jogo dos Animais", JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            JOptionPane.showMessageDialog(null, "Pense em um animal!", "Jogo dos Animais", JOptionPane.INFORMATION_MESSAGE);
            List<Caracteristica> caracteristicasDaRodada = controleCaracteristicas.caracteristicasSorteadas();
            for (Caracteristica caracteristica : new ArrayList<>(caracteristicasDaRodada)) {
                boolean flagSkip = false;
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
                            qualAnimalUsuarioPensou(animal);
                            flagSkip = true;
                        }
                    }
                } else if (caracteristicasDaRodada.indexOf(caracteristica) == caracteristicasDaRodada.size() - 1) {
                    qualAnimalUsuarioPensou(caracteristica.getAnimais().get(caracteristica.getAnimais().size() - 1));
                    flagSkip = true;
                }
                if (flagSkip) {
                    break;
                }
            }
        }
    }

    public void finalizarJogo() {
        JOptionPane.showMessageDialog(null, "Obrigado por Jogar!", "Jogo dos Animais", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public void reiniciar() {
        animaisRespondidos = new ArrayList<>();
        controleCaracteristicas = new ControleCaracteristicas();
        Animal animal = new Animal("Tubarão");
        animal.addCaracteristicas(controleCaracteristicas.getCaracteristicaByName("vive na água"));
        animaisRespondidos.add(animal);
    }
}
