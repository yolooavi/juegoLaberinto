import java.awt.*;
import java.util.*;
import java.util.List;

public class LaberintoDificil extends Laberinto {
    public LaberintoDificil() {
        super(40, 20, "Difícil");
        setTitle("Laberinto - Difícil");
    }

    @Override
    protected void generarLaberinto() {
        Random rand = new Random();

        for (int[] row : laberinto)
            Arrays.fill(row, 1);

        Stack<Point> stack = new Stack<>();

        int startX = 1, startY = 0;
        int entryX = 1, entryY = 1;
        laberinto[startY][startX] = 0;
        laberinto[entryY][entryX] = 0;
        stack.push(new Point(entryX, entryY));

        while (!stack.isEmpty()) {
            Point current = stack.peek();
            int x = current.x, y = current.y;
            List<Point> neighbors = new ArrayList<>();

            if (y > 1 && laberinto[y - 2][x] == 1) neighbors.add(new Point(x, y - 2));
            if (y < tamano - 2 && laberinto[y + 2][x] == 1) neighbors.add(new Point(x, y + 2));
            if (x > 1 && laberinto[y][x - 2] == 1) neighbors.add(new Point(x - 2, y));
            if (x < tamano - 2 && laberinto[y][x + 2] == 1) neighbors.add(new Point(x + 2, y));

            if (!neighbors.isEmpty()) {
                Point next = neighbors.get(rand.nextInt(neighbors.size()));
                int nx = next.x;
                int ny = next.y;

                laberinto[(y + ny) / 2][(x + nx) / 2] = 0;
                laberinto[ny][nx] = 0;
                stack.push(next);
            } else {
                stack.pop();
            }
        }

        laberinto[tamano - 1][tamano - 2] = 0;

        for (int i = 0; i < 50; i++) {
            int x = 1 + 2 * rand.nextInt((tamano - 2) / 2);
            int y = 1 + 2 * rand.nextInt((tamano - 2) / 2);
            if (laberinto[y][x] == 1) {
                laberinto[y][x] = 0;
            }
        }
    }
}
