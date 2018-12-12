package com.amitph.spring.songs.service;

import com.amitph.spring.songs.model.SongDto;
import com.amitph.spring.songs.repo.Song;
import com.amitph.spring.songs.repo.SongId;
import com.amitph.spring.songs.repo.SongsRepository;
import com.amitph.spring.songs.web.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongsService {
    @Autowired SongsRepository repository;

    public Song addSong(SongDto dto) {
        return repository.save(dtoToSong(dto));
    }

    public Song find(SongDto dto) {
        return repository.findById(dtoToSongId(dto)).orElseThrow(SongNotFoundException::new);
    }

    private Song dtoToSong(SongDto dto) {
        return new Song(dtoToSongId(dto), dto.getDuration(), dto.getGenre(), dto.getReleaseDate(), dto.getRating(), dto.getDownloadUrl());
    }

    private SongId dtoToSongId(SongDto dto) {
        return new SongId(dto.getName(), dto.getAlbum(), dto.getArtist());
    }
}