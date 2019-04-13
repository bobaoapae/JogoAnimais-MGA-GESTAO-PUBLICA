package controle;

import modelo.Caracteristica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ControleCaracteristicas {
    private HashMap<String, Caracteristica> caracteristicaHashMap;

    public ControleCaracteristicas() {
        caracteristicaHashMap = new HashMap<>();
    }

    public Caracteristica getCaracteristicaByName(String name) {
        if (!caracteristicaHashMap.containsKey(name)) {
            caracteristicaHashMap.put(name, new Caracteristica(name));
        }
        return caracteristicaHashMap.get(name);
    }

    public List<Caracteristica> caracteristicasSorteadas() {
        List<Caracteristica> caracteristicas = new ArrayList<>(caracteristicaHashMap.values());
        caracteristicas.sort(new Comparator<Caracteristica>() {
            @Override
            public int compare(Caracteristica o1, Caracteristica o2) {
                return Integer.compare(o1.getAnimais().size(), o2.getAnimais().size());
            }
        });
        return caracteristicas;
    }
}
