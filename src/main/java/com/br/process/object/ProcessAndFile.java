package com.br.process.object;

import com.br.process.model.Process;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProcessAndFile {
    private Process process;
    private MultipartFile file;
}
