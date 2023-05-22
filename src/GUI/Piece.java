package GUI;

public class Piece {

    public int xPosition;
    public int yPosition;
    public boolean out;
    public boolean upgraded;
    public char type;
    public int id;
    public String imageSource;

    public Piece(int xPosition, int yPosition, boolean out, boolean upgraded, char type, int id) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.out = out;
        this.upgraded = upgraded;
        this.type = type;
        this.id = id;

        switch (type) {
            case 'K':
                imageSource = "src/Assets/w_k.png";
                break;
            case 'G':
                imageSource = "src/Assets/w_g.png";
                break;
            case 'S':
                imageSource = "src/Assets/w_s.png";
                break;
            case 'L':
                imageSource = "src/Assets/w_l.png";
                break;
            case 'B':
                imageSource = "src/Assets/w_b.png";
                break;
            case 'P':
                imageSource = "src/Assets/w_p.png";
                break;
            case 'k':
                imageSource = "src/Assets/b_k.png";
                break;
            case 'g':
                imageSource = "src/Assets/b_g.png";
                break;
            case 's':
                imageSource = "src/Assets/b_s.png";
                break;
            case 'l':
                imageSource = "src/Assets/b_l.png";
                break;
            case 'b':
                imageSource = "src/Assets/b_b.png";
                break;
            case 'p':
                imageSource = "src/Assets/b_p.png";
                break;
            default:
                imageSource = "src/Assets/empty.png";
                break;
        }
    }

    public boolean transfer(Piece piece, int toX, int toY, GameBoard gameBoard) {

        if (!gameBoard.inside(toX, toY)) return false;
        if (!canGo(piece, toX, toY, gameBoard)) return false;

        GameManagement.setSelectedPiece(null);
        GameManagement.whiteTurn = !GameManagement.whiteTurn;
        GameManagement.update();
        return true;
    }

    public String getImageSource() {
        return imageSource;
    }

    private boolean canGo(Piece piece, int toX, int toY, GameBoard gameBoard) {

        int x = piece.xPosition;
        int y = piece.yPosition;

        if (toX == x && toY == y) return false;

        boolean[][] mat = new boolean[6][6];
        for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) mat[i][j] = false;

        System.out.println(piece.type + " IS ABOUT TO MOVE...");

        if (piece.type == 'K') {

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int dx = Math.abs(toX - x);
                    int dy = Math.abs(toY - y);

                    if (dx <= 1 && dy <= 1) {
                        mat[i][j] = true;
                    }
                    else {
                        mat[i][j] = false;
                    }
                }
            }

        }
        else if (piece.type == 'G') {

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int dx = Math.abs(toX - x);
                    int dy = Math.abs(toY - y);

                    if (dx <= 1 && dy <= 1 && !(dx == 1 && (toY - piece.yPosition == -1))) {
                        mat[i][j] = true;
                    }
                    else {
                        mat[i][j] = false;
                    }
                }
            }

        }
        else if (piece.type == 'S') {

            if (piece.upgraded) {

                moveNorth(piece, 2, mat, gameBoard);

                moveSouth(piece, 2, mat, gameBoard);

                moveEast(piece, 2, mat, gameBoard);

                moveWest(piece, 2, mat, gameBoard);

                moveNorthEast(piece, 2, mat, gameBoard);

                moveSouthEast(piece, 2, mat, gameBoard);

                moveSouthWest(piece, 2, mat, gameBoard);

                moveNorthWest(piece, 2, mat, gameBoard);

            }
            else {

                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int dx = Math.abs(toX - x);
                        int dy = Math.abs(toY - y);

                        if (dx <= 1 && dy <= 1 && !((dx == 1 && dy == 0) || (dx == 0 && toY - piece.yPosition == -1))) {
                            mat[i][j] = true;
                        }
                        else {
                            mat[i][j] = false;
                        }
                    }
                }

            }

        }
        else if (piece.type == 'B') {

            if (piece.upgraded) {

                moveNorth(piece, 1, mat, gameBoard);

                moveSouth(piece, 1, mat, gameBoard);

                moveEast(piece, 1, mat, gameBoard);

                moveWest(piece, 1, mat, gameBoard);

                moveNorthEast(piece, 5, mat, gameBoard);

                moveSouthEast(piece, 5, mat, gameBoard);

                moveSouthWest(piece, 5, mat, gameBoard);

                moveNorthWest(piece, 5, mat, gameBoard);

            }
            else {

                moveNorthEast(piece, 5, mat, gameBoard);

                moveNorthWest(piece, 5, mat, gameBoard);

            }

        }
        else if (piece.type == 'L') {

            if (piece.upgraded) {

                moveNorth(piece, 5, mat, gameBoard);

                moveSouth(piece, 5, mat, gameBoard);

                moveEast(piece, 5, mat, gameBoard);

                moveWest(piece, 5, mat, gameBoard);

            }
            else {

                moveNorth(piece, 5, mat, gameBoard);

            }

        }
        else if (piece.type == 'P') {

            if (piece.upgraded) {

                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int dx = Math.abs(toX - x);
                        int dy = Math.abs(toY - y);

                        if (dx <= 1 && dy <= 1 && !(dx == 1 && (toY - piece.yPosition == -1))) {
                            mat[i][j] = true;
                        }
                        else {
                            mat[i][j] = false;
                        }
                    }
                }

            }
            else {

                moveNorth(piece, 1, mat, gameBoard);

            }

        }
        else if (piece.type == 'k') {

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int dx = Math.abs(toX - x);
                    int dy = Math.abs(toY - y);

                    if (dx <= 1 && dy <= 1) {
                        mat[i][j] = true;
                    }
                    else {
                        mat[i][j] = false;
                    }
                }
            }

        }
        else if (piece.type == 'g') {

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int dx = Math.abs(toX - x);
                    int dy = Math.abs(toY - y);

                    if (dx <= 1 && dy <= 1 && !(dx == 1 && (toY - piece.yPosition == 1))) {
                        mat[i][j] = true;
                    }
                    else {
                        mat[i][j] = false;
                    }
                }
            }

        }
        else if (piece.type == 's') {

            if (piece.upgraded) {

                moveNorth(piece, 2, mat, gameBoard);

                moveSouth(piece, 2, mat, gameBoard);

                moveEast(piece, 2, mat, gameBoard);

                moveWest(piece, 2, mat, gameBoard);

                moveNorthEast(piece, 2, mat, gameBoard);

                moveSouthEast(piece, 2, mat, gameBoard);

                moveSouthWest(piece, 2, mat, gameBoard);

                moveNorthWest(piece, 2, mat, gameBoard);

            }
            else {

                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int dx = Math.abs(toX - x);
                        int dy = Math.abs(toY - y);

                        if (dx <= 1 && dy <= 1 && !((dx == 1 && dy == 0) || (dx == 0 && toY - piece.yPosition == 1))) {
                            mat[i][j] = true;
                        }
                        else {
                            mat[i][j] = false;
                        }
                    }
                }

            }

        }
        else if (piece.type == 'b') {

            if (piece.upgraded) {

                moveNorth(piece, 1, mat, gameBoard);

                moveSouth(piece, 1, mat, gameBoard);

                moveEast(piece, 1, mat, gameBoard);

                moveWest(piece, 1, mat, gameBoard);

                moveNorthEast(piece, 5, mat, gameBoard);

                moveSouthEast(piece, 5, mat, gameBoard);

                moveSouthWest(piece, 5, mat, gameBoard);

                moveNorthWest(piece, 5, mat, gameBoard);

            }
            else {

                moveSouthEast(piece, 5, mat, gameBoard);

                moveSouthWest(piece, 5, mat, gameBoard);

            }

        }
        else if (piece.type == 'l') {

            if (piece.upgraded) {

                moveNorth(piece, 5, mat, gameBoard);

                moveSouth(piece, 5, mat, gameBoard);

                moveEast(piece, 5, mat, gameBoard);

                moveWest(piece, 5, mat, gameBoard);

            }
            else {

                moveSouth(piece, 5, mat, gameBoard);

            }

        }
        else if (piece.type == 'p') {

            if (piece.upgraded) {

                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int dx = Math.abs(toX - x);
                        int dy = Math.abs(toY - y);

                        if (dx <= 1 && dy <= 1 && !(dx == 1 && (toY - piece.yPosition == 1))) {
                            mat[i][j] = true;
                        }
                        else {
                            mat[i][j] = false;
                        }
                    }
                }

            }
            else {

                moveSouth(piece, 1, mat, gameBoard);

            }

        }

        if (mat[toX][toY] && move(piece, toX, toY, gameBoard)) {
            System.out.println("TRANSFERD: " + piece.type + " to " + piece.xPosition + "," + piece.yPosition);
            return true;
        }
        return false;
    }

    public boolean move(Piece piece, int toX, int toY, GameBoard gameBoard) {

        System.out.println(piece.type + " IS GOING TO MOVE");

        if (gameBoard.cells[toX][toY].getInnerPiece() == null) {

            if (piece.out) {

                System.out.println("FROM OUT");

                if (piece.xPosition == -1) {
                    GameManagement.whitePlayer.removePiece(piece);
                }
                else {
                    GameManagement.blackPlayer.removePiece(piece);
                }

                piece.out = false;
                gameBoard.cells[toX][toY].setPiece(piece);
                piece.xPosition = toX;
                piece.yPosition = toY;

                GameManagement.setSelectedPiece(null);
                GameManagement.whiteTurn = !GameManagement.whiteTurn;
                GameManagement.update();

                return true;
            }

            gameBoard.cells[piece.xPosition][piece.yPosition].emptyCell();
            gameBoard.cells[toX][toY].setPiece(piece);
            piece.xPosition = toX;
            piece.yPosition = toY;

            piece.upgrade(gameBoard);

            return true;
        }
        else {
            if (isWhite(gameBoard.cells[toX][toY].getInnerPiece()) ^ isWhite(piece)) {

                System.out.println(piece.type + " IS GOING TO REMOVE " + gameBoard.cells[toX][toY].getInnerPiece().type);

                if (gameBoard.cells[toX][toY].getInnerPiece().type == 'k') {
                    System.out.println("white wins!");
                    GameManagement.finished = true;
                    GameManagement.gameFrame.gameOver(GameManagement.whitePlayer);
                }
                else if (gameBoard.cells[toX][toY].getInnerPiece().type == 'K') {
                    System.out.println("black wins!");
                    GameManagement.finished = true;
                    GameManagement.gameFrame.gameOver(GameManagement.blackPlayer);
                }

                GameManagement.removePiece(gameBoard.cells[toX][toY].getInnerPiece());
                gameBoard.cells[piece.xPosition][piece.yPosition].setPiece(null);
                gameBoard.cells[toX][toY].setPiece(piece);
                piece.xPosition = toX;
                piece.yPosition = toY;

                piece.upgrade(gameBoard);

                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean isWhite() {
        if (this.type >= 'A' && this.type <= 'Z') return true;
        return false;
    }

    public boolean isWhite(Piece piece) {
        if (piece.type >= 'A' && piece.type <= 'Z') return true;
        return false;
    }

    public void moveNorth(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x, y+k)) break;

            if (gameBoard.cells[x][y+k].getInnerPiece() == null) {
                mat[x][y+k] = true;
            }
            else {
                if (gameBoard.cells[x][y+k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x][y+k] = true;
                    break;
                }
                else {
                    mat[x][y+k] = false;
                    break;
                }
            }
        }
    }

    public void moveSouth(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x, y-k)) break;

            if (gameBoard.cells[x][y-k].getInnerPiece() == null) {
                mat[x][y-k] = true;
            }
            else {
                if (gameBoard.cells[x][y-k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x][y-k] = true;
                    break;
                }
                else {
                    mat[x][y-k] = false;
                    break;
                }
            }
        }
    }

    public void moveEast(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x+k, y)) break;

            if (gameBoard.cells[x+k][y].getInnerPiece() == null) {
                mat[x+k][y] = true;
            }
            else {
                if (gameBoard.cells[x+k][y].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x+k][y] = true;
                    break;
                }
                else {
                    mat[x+k][y] = false;
                    break;
                }
            }
        }
    }

    public void moveWest(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x-k, y)) break;

            if (gameBoard.cells[x-k][y].getInnerPiece() == null) {
                mat[x-k][y] = true;
            }
            else {
                if (gameBoard.cells[x-k][y].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x-k][y] = true;
                    break;
                }
                else {
                    mat[x-k][y] = false;
                    break;
                }
            }
        }
    }

    public void moveNorthEast(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x+k, y+k)) break;

            if (gameBoard.cells[x+k][y+k].getInnerPiece() == null) {
                mat[x+k][y+k] = true;
            }
            else {
                if (gameBoard.cells[x+k][y+k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x+k][y+k] = true;
                    break;
                }
                else {
                    mat[x+k][y+k] = false;
                    break;
                }
            }
        }
    }

    public void moveSouthEast(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x+k, y-k)) break;

            if (gameBoard.cells[x+k][y-k].getInnerPiece() == null) {
                mat[x+k][y-k] = true;
            }
            else {
                if (gameBoard.cells[x+k][y-k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x+k][y-k] = true;
                    break;
                }
                else {
                    mat[x+k][y-k] = false;
                    break;
                }
            }
        }
    }

    public void moveSouthWest(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x-k, y-k)) break;

            if (gameBoard.cells[x-k][y-k].getInnerPiece() == null) {
                mat[x-k][y-k] = true;
            }
            else {
                if (gameBoard.cells[x-k][y-k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x-k][y-k] = true;
                    break;
                }
                else {
                    mat[x-k][y-k] = false;
                    break;
                }
            }
        }
    }

    public void moveNorthWest(Piece piece, int n, boolean[][] mat, GameBoard gameBoard) {
        int x = piece.xPosition, y = piece.yPosition;

        for (int k = 1; k <= n; k++) {
            if (!gameBoard.inside(x-k, y+k)) break;

            if (gameBoard.cells[x-k][y+k].getInnerPiece() == null) {
                mat[x-k][y+k] = true;
            }
            else {
                if (gameBoard.cells[x-k][y+k].getInnerPiece().isWhite() ^ piece.isWhite()) {
                    mat[x-k][y+k] = true;
                    break;
                }
                else {
                    mat[x-k][y+k] = false;
                    break;
                }
            }
        }
    }

    public void upgrade(GameBoard gameBoard) {

        if (this.upgraded) return;

        if (this.isWhite()) {
            if (this.yPosition < 4) return;

            if (this.type == 'P' || this.type == 'L' || this.type == 'B' || this.type == 'S') {
                System.out.println(this.type + " IS UPGRADED!");
                this.upgraded = true;
            }
        }
        else {
            if (this.yPosition > 2) return;

            if (this.type == 'p' || this.type == 'l' || this.type == 'b' || this.type == 's') {
                System.out.println(this.type + " IS UPGRADED!");
                this.upgraded = true;
            }
        }

    }
}
