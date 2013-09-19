package com.github.donttouchit.geom;

import com.github.donttouchit.game.Level;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class LevelChecker {
	private int[][] distance;
	private GridPoint[][] prev;
	private Queue<GridPoint> queue;

	public boolean checkExitWay(Level level) {
		distance = new int[level.getColumns()][level.getRows()];
		prev = new GridPoint[level.getColumns()][level.getRows()];
		queue = new ArrayDeque<GridPoint>();

		for (int index = 0; index < level.getColumns(); ++index) {
			Arrays.fill(distance[index], -1);
			Arrays.fill(prev[index], new GridPoint(-1, -1));
		}

		GridPoint enter = level.getEnterPoint();
		distance[enter.x][enter.y] = 0;
		queue.add(enter);

		while (!queue.isEmpty()) {
			GridPoint p = queue.poll();
			for (int index = 0; index < 4; ++index) {
				Direction direction = Direction.values()[index + 1];
				GridPoint np = new GridPoint(p.x + direction.getPoint().x, p.y + direction.getPoint().y);
				if ((level.isEmpty(np.x, np.y) || np.equals(level.getExitPoint())) && distance[np.x][np.y] == -1) {
					distance[np.x][np.y] = distance[p.x][p.y] + 1;
					prev[np.x][np.y] = new GridPoint(p);
					queue.add(np);
				}
			}
		}

		return distance[level.getExitPoint().x][level.getExitPoint().y] != -1;
	}
}
