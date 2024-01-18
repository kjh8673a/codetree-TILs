import java.io.*;
import java.util.*;

public class Main {
    static class GroundInfo {
        int x;
        int y;
        int tree;
        boolean wall;
        int poison;

        public GroundInfo(int x, int y, int tree, boolean wall, int poison) {
            this.x = x;
            this.y = y;
            this.tree = tree;
            this.wall = wall;
            this.poison = poison;
        }
    }

    static int[][] vectorForTree = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int[][] vectorForPoison = { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

    static int groundSize, period, poisonRange, poisonExpire;
    static GroundInfo[][] ground;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        groundSize = Integer.parseInt(st.nextToken());
        period = Integer.parseInt(st.nextToken());
        poisonRange = Integer.parseInt(st.nextToken());
        poisonExpire = Integer.parseInt(st.nextToken());

        ground = new GroundInfo[groundSize][groundSize];
        for(int i = 0; i < groundSize; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < groundSize; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num >= 0) {
                    ground[i][j] = new GroundInfo(i, j, num, false, 0);
                }else {
                    ground[i][j] = new GroundInfo(i, j, 0, true, 0);
                }
            }
        }

        play(0, 0);
    }

    private static void play(int year, int treeCount) {
        if(year == period) {
            System.out.println(treeCount);
            return;
        }

        // 1. 인접 4칸 중 나무의 수만큼 나무 성장 + 나무 위치 반환
        Queue<GroundInfo> treeToSpreadQueue = treeGrowth();

        // 2. 나무 번식
        spreadTree(treeToSpreadQueue);

        // 3. 유효기간지난 제초제 없애기 + 제초제뿌렸을 때 가장 많이 죽는 칸 찾기
        GroundInfo groundToBePoisoned = erasePoison();
        
        // 4. 제초제 뿌리기
        int killedTree = dropPoison(groundToBePoisoned);

        // 5. 다음 년도로 진행
        play(year + 1, treeCount + killedTree);
    }

    private static Queue<GroundInfo> treeGrowth() {
        Queue<GroundInfo> treeToSpreadQueue = new LinkedList<>();
        for(int i = 0; i < groundSize; i++) {
            for(int j = 0; j < groundSize; j++) {
                GroundInfo now = ground[i][j];
                if(now.tree > 0) {
                    int closeTree = 0;
                    for(int k = 0; k < 4; k++) {
                        int nr = i + vectorForTree[k][0];
                        int nc = j + vectorForTree[k][1];

                        if(isInBound(nr, nc) && ground[nr][nc].tree > 0) {
                            closeTree++;
                        }
                    }
                    ground[i][j] = new GroundInfo(i, j, now.tree + closeTree, now.wall, now.poison);

                    treeToSpreadQueue.add(ground[i][j]);
                }
            }
        }

        return treeToSpreadQueue;
    }

    private static void spreadTree(Queue<GroundInfo> treeToSpreadQueue) {
        while(!treeToSpreadQueue.isEmpty()) {
            GroundInfo now = treeToSpreadQueue.poll();
            int closeEmptyGround = 0;

            boolean[] spreadPossibleArea = new boolean[4];
            for(int k = 0; k < 4; k++) {
                int nr = now.x + vectorForTree[k][0];
                int nc = now.y + vectorForTree[k][1];

                if(!isInBound(nr, nc)) {
                    continue;
                }

                if(ground[nr][nc].tree > 0 || ground[nr][nc].wall || ground[nr][nc].poison > 0) {
                    continue;
                }

                closeEmptyGround++;
                spreadPossibleArea[k] = true;
            }

            if(closeEmptyGround == 0) {
                continue;
            }

            int spreadAmout = now.tree / closeEmptyGround;
            for(int k = 0; k < 4; k++) {
                if(!spreadPossibleArea[k]) {
                    continue;
                }

                int nr = now.x + vectorForTree[k][0];
                int nc = now.y + vectorForTree[k][1];
                GroundInfo next = ground[nr][nc];

                ground[nr][nc] = new GroundInfo(nr, nc, next.tree + spreadAmout, next.wall, next.poison);
            }
        }
    }

    private static GroundInfo erasePoison() {
        int maxKilled = 0;
        int maxX = 0;
        int maxY = 0;

        for(int i = 0; i < groundSize; i++) {
            for(int j = 0; j < groundSize; j++) {
                GroundInfo now = ground[i][j];
                if(now.tree > 0) {
                    int tmpKilled = now.tree;

                    for(int k = 0; k < 4; k++) {
                        int nr = now.x + vectorForPoison[k][0];
                        int nc = now.y + vectorForPoison[k][1];

                        while(isInBound(nr, nc)) {
                            GroundInfo next = ground[nr][nc];
                            if(next.tree == 0) {
                                break;
                            }

                            tmpKilled += next.tree;
                            nr += vectorForPoison[k][0];
                            nc += vectorForPoison[k][1];
                        }
                    }

                    if(tmpKilled > maxKilled) {
                        maxKilled = tmpKilled;
                        maxX = i;
                        maxY = j;
                    }
                }
                
                if(now.poison > 0) {
                    ground[i][j] = new GroundInfo(i, j, now.tree, now.wall, now.poison - 1);
                }
            }
        }

        return ground[maxX][maxY];
    }

    private static int dropPoison(GroundInfo poisonDropSpot) {
        int killedTree = 0;

        killedTree += poisonDropSpot.tree;
        ground[poisonDropSpot.x][poisonDropSpot.y] = new GroundInfo(poisonDropSpot.x, poisonDropSpot.y, 0, poisonDropSpot.wall, poisonExpire);

        for(int k = 0; k < 4; k++) {
            int nr = poisonDropSpot.x + vectorForPoison[k][0];
            int nc = poisonDropSpot.y + vectorForPoison[k][1];

            while(isInBound(nr, nc)) {
                GroundInfo next = ground[nr][nc];
                if(next.tree == 0) {
                    ground[nr][nc] = new GroundInfo(nr, nc, 0, next.wall, poisonExpire);
                    break;
                }

                killedTree += next.tree;
                ground[nr][nc] = new GroundInfo(nr, nc, 0, next.wall, poisonExpire);
                nr += vectorForPoison[k][0];
                nc += vectorForPoison[k][1];
            }
        }

        return killedTree;
    }

    private static boolean isInBound(int r, int c) {
        if(r < 0 || c < 0 || r >= groundSize || c >= groundSize) {
            return false;
        }
        return true;
    }
}