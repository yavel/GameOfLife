package com.yavel;

import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    public void testCountNeighborsEmptyBoard() {
        Board b = new Board(3,3);
        b.set(0,0, 0);
        b.set(0, 1, 0);
        b.set(0, 2, 0);

        b.set(1,0, 0);
        b.set(1, 1, 0);
        b.set(1, 2, 0);

        b.set(2,0, 0);
        b.set(2, 1, 0);
        b.set(2, 2, 0);

        assert b.countNeighbors(0, 0) == 0;
        assert b.countNeighbors(1, 1)  == 0;
        assert b.countNeighbors(2, 2) == 0;
    }

    @Test
    public void testCountNeighborsFullBoard() {
        Board b = new Board(3,3);
        b.set(0,0, 1);
        b.set(0, 1, 1);
        b.set(0, 2, 1);

        b.set(1,0, 1);
        b.set(1, 1, 1);
        b.set(1, 2, 1);

        b.set(2,0, 1);
        b.set(2, 1, 1);
        b.set(2, 2, 1);

        assert b.countNeighbors(0, 0) == 3;
        assert b.countNeighbors(1, 1)  == 8;
        assert b.countNeighbors(2, 2) == 3;
    }
}