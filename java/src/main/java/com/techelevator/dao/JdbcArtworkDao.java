package com.techelevator.dao;

import java.util.Random;

public class JdbcArtworkDao implements ArtworkDao{

    @Override
    public int randomArtworkAny() {
        Random random = new Random();
        int randomNumber = random.nextInt(471581);
    }
}
