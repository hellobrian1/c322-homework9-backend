package c322spring2024homework2.c322spring2024homework2.controllers;

import c322spring2024homework2.c322spring2024homework2.model.GuitarData;
import c322spring2024homework2.c322spring2024homework2.repository.InventoryFileRepository;
import c322spring2024homework2.c322spring2024homework2.repository.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/inventory")
public class InventoryController{
    private InventoryFileRepository inventoryFileRepository;
    private InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository, InventoryFileRepository inventoryFileRepository){
        this.inventoryRepository = inventoryRepository;
        this.inventoryFileRepository = inventoryFileRepository;
    }


    @GetMapping
    public Iterable<GuitarData> findAll(){
        try{
            return inventoryRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    public String add(@RequestBody GuitarData data){
        try{
            System.out.println("Adding guitar to inventory: " + data.getSerialNumber());
            return inventoryRepository.save(data).getSerialNumber();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<GuitarData> find(@PathVariable String serialNumber){
        try{
            //GuitarData guitar = inventoryRepository.find(serialNumber);
            Optional<GuitarData> guitar = inventoryRepository.findById(Integer.valueOf(serialNumber));
            if(guitar != null){
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(guitar.get());
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

//    @GetMapping("/find")
//    public GuitarData getGuitar(@RequestParam String serialNumber) {
//        try {
//            FileReader r = new FileReader("guitars_database.txt");
//            BufferedReader bufferedReader = new BufferedReader(r);
//
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] guitarData = line.split(",");
//                if (guitarData[0].equals(serialNumber)) {
//                    return new GuitarData(guitarData[0], Double.parseDouble(guitarData[1]), guitarData[2],
//                            guitarData[3], guitarData[4], guitarData[5], guitarData[6]);
//                }
//            }
//            bufferedReader.close();
//            r.close();
//            return null;
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + e.getMessage());
//            return null;
//        }
//    }

}