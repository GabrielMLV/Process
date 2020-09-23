package com.br.process.controller;
import com.br.process.model.DBFile;
import com.br.process.model.Process;
import com.br.process.object.ProcessAndFile;
import com.br.process.object.ResultListCriteria;
import com.br.process.object.SearchCriteria;
import com.br.process.repository.ProcessRepository;
import com.br.process.service.DBFileStorageService;
import com.br.process.service.UploadFileResponse;
import org.apache.coyote.Response;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/process"})
public class ProcessController {

    private final ProcessRepository repository;
    private final DBFileStorageService dbFileStorageService;
    public ProcessController(ProcessRepository processRepository, DBFileStorageService dbFileStorageService){
        this.repository = processRepository;
        this.dbFileStorageService = dbFileStorageService;
    }

    // GET ALL PROCESS NO PAGINATION
    @CrossOrigin
    @GetMapping()
    public ResponseEntity <?> findAll(){
        List<Process> process = repository.findAll();
        return ResponseEntity.ok().body(process);
    }

    // GET ALL PROCESS BY SEARCH AND PAGINATION
    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity <?> findAllByCriteria(@RequestBody SearchCriteria criteria){
        ResultListCriteria result = new ResultListCriteria();
        String search_criteria = "%"+criteria.getSearch_criteria().trim()+"%";
        int items_per_page = criteria.getItems_per_page();
        int asked_page = criteria.getAsked_page();
        asked_page = items_per_page * asked_page;
        long countProcess = repository.count();
        List<Process> process = repository.findAllByCriteria(search_criteria, asked_page, items_per_page);
        long total_pages = countProcess / items_per_page;
        result.setCurrent_page(asked_page);
        result.setList_items(process);
        result.setNumber_registers(countProcess);
        result.setTotal_pages(total_pages);

        return ResponseEntity.ok().body(result);
    }

    //INSERT PROCESS
    @CrossOrigin
    @PostMapping
    public ResponseEntity <?> create(@RequestBody Process item){
        ResponseEntity response;
        item.setDeleted(false);
        item.setCreated_at(new Date());
        //UploadFileResponse up = uploadFile(item.getFile());
        //if(up != null){
           // process.setFilename_process(up.getId());
            response = ResponseEntity.ok().body(repository.save(item));
        //}else{
          //  response = ResponseEntity.badRequest().build();
       // }
        return response;
    }

    // GET PROCESS BY ID
    @CrossOrigin
    @GetMapping(path = {"/{id}"})
    public ResponseEntity <?> findById(@PathVariable long id){
        ResponseEntity <?> response;
        if(!validateId(id)){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // UPDATE PROCESS
    @CrossOrigin
    @PutMapping(value="/{id}")
    public ResponseEntity <?> update(@PathVariable("id") long id, @RequestBody Process process) {
        ResponseEntity <?> response;
        if(!validateId(id)){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id).map(record -> {
                record.setName_requester(process.getName_requester());
                record.setZip_code(process.getZip_code());
                record.setCity(process.getCity());
                record.setDistrict(process.getDistrict());
                record.setStreet(process.getStreet());
                record.setNum_house(process.getNum_house());
                record.setDate_process(process.getDate_process());
                record.setFilename_process(process.getFilename_process());
                record.setCreated_at(record.getCreated_at()); //mantendo os valores de criação
                //save client
                Process updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // DELETE
    @CrossOrigin
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        ResponseEntity <?> response;
        if(!validateId(id)){
            return ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id).map(record -> {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    public UploadFileResponse uploadFile(MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/process/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }


    public Boolean validateId(long id){
        boolean res;
        if(id <= 0){
            res = false;
        }else{
            res = true;
        }
        return res;
    }


}
