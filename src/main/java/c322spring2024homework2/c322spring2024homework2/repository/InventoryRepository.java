package c322spring2024homework2.c322spring2024homework2.repository;

import c322spring2024homework2.c322spring2024homework2.model.GuitarData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;



@Repository
public interface InventoryRepository
        extends CrudRepository<GuitarData, Integer> {




}