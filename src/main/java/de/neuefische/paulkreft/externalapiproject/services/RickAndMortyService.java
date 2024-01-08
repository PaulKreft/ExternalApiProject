package de.neuefische.paulkreft.externalapiproject.services;

import de.neuefische.paulkreft.externalapiproject.models.Character;
import de.neuefische.paulkreft.externalapiproject.models.api.RickAndMortyCharacterResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class RickAndMortyService {
    private final RestClient restClient = RestClient.builder().baseUrl("https://rickandmortyapi.com/api").build();

    public List<Character> getCharacters(Optional<String> status) {
        String uri = "/character";

        if(status.isPresent()) {
            uri += "?status=" + status.get();
        }

        RickAndMortyCharacterResponse response = restClient.get().uri(uri).retrieve().body(RickAndMortyCharacterResponse.class);

        if(response == null) {
            return List.of();
        }

        return response.results();
    }

    public Character getCharacterById(int id) {
        return restClient.get().uri("/character/" + id).retrieve().body(Character.class);
    }
}
