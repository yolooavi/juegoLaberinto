import java.awt.*;
import java.util.*;
import java.util.List;

public class LaberintoMedio extends Laberinto {
    public LaberintoMedio() {
        super(25, 25);
        setTitle("Laberinto - Medio");
    }

    @Override
    protected void generarLaberinto() {
        Random rand = new Random();

        for (int[] fila : laberinto)
            Arrays.fill(fila, 1);

        Stack<Point> pila = new Stack<>();

        int inicioX = 1, inicioY = 0;
        int entradaX = 1, entradaY = 1;
        laberinto[inicioY][inicioX] = 0;
        laberinto[entradaY][entradaX] = 0;
        pila.push(new Point(entradaX, entradaY));

        while (!pila.isEmpty()) {
            Point actual = pila.peek();
            int x = actual.x, y = actual.y;
            List<Point> vecinos = new ArrayList<>();

            if (y > 2 && laberinto[y - 2][x] == 1) vecinos.add(new Point(x, y - 2));
            if (y < tamano - 3 && laberinto[y + 2][x] == 1) vecinos.add(new Point(x, y + 2));
            if (x > 2 && laberinto[y][x - 2] == 1) vecinos.add(new Point(x - 2, y));
            if (x < tamano - 3 && laberinto[y][x + 2] == 1) vecinos.add(new Point(x + 2, y));

            if (!vecinos.isEmpty()) {
                Point siguiente = vecinos.get(rand.nextInt(vecinos.size()));
                int nx = siguiente.x;
                int ny = siguiente.y;

                laberinto[(y + ny) / 2][(x + nx) / 2] = 0;
                laberinto[ny][nx] = 0;
                pila.push(siguiente);
            } else {
                pila.pop();
            }
        }

        for (int i = 0; i < 30; i++) {
            int x = 1 + 2 * rand.nextInt((tamano - 2) / 2);
            int y = 1 + 2 * rand.nextInt((tamano - 2) / 2);
            List<Point> direcciones = Arrays.asList(
                new Point(0, -1), new Point(0, 1),
                new Point(-1, 0), new Point(1, 0)
            );
            Collections.shuffle(direcciones);
            for (Point d : direcciones) {
                int nx = x + d.x;
                int ny = y + d.y;
                if (nx > 0 && ny > 0 && nx < tamano - 1 && ny < tamano - 1 && laberinto[ny][nx] == 1) {
                    laberinto[ny][nx] = 0;
                    break;
                }
            }
        }

        laberinto[tamano - 1][tamano - 2] = 0;
    }
}
