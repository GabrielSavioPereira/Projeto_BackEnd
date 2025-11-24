package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import com.projeto_backend.controle_gastos.dtos.TransacaoRequest;
import com.projeto_backend.controle_gastos.dtos.TransacaoResponse;
import com.projeto_backend.controle_gastos.mappers.TransacaoMapper;
import com.projeto_backend.controle_gastos.models.Transacoes;
import com.projeto_backend.controle_gastos.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;



import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public TransacaoResponse create(TransacaoRequest dto) {
        Transacoes transacoes= TransacaoMapper.toEntity(dto);
        Transacoes saved = repository.save(transacoes);
        return TransacaoMapper.toResponse(saved);
    }

    public Page<TransacaoResponse> getAll(UUID usuarioId, TipoTransacao tipo, LocalDate inicio, LocalDate fim, Pageable pageable) {
        Page<Transacoes> page = repository.findByUsuarioIdAndTipoAndData(usuarioId, tipo, inicio, fim, pageable);
        return page.map(TransacaoMapper::toResponse);
    }

    public TransacaoResponse getOne(UUID id) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));
        return TransacaoMapper.toResponse(t);
    }

    public TransacaoResponse update(UUID id, TransacaoRequest dto) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));

        t.setDescricao(dto.descricao());
        t.setValor(dto.valor());
        t.setData(dto.dataMovimentacao());
        t.setTipo(dto.tipo());
        t.setContaId(dto.contaId());
        t.setCategoriaId(dto.categoriaId());
        t.setUsuarioId(dto.usuarioId());

        return TransacaoMapper.toResponse(repository.save(t));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public int importarPlanilha(MultipartFile file) {
        int count = 0;

        try(InputStream is = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(is)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String descricao = row.getCell(0).getStringCellValue();
                BigDecimal valor = BigDecimal.valueOf(row.getCell(1).getNumericCellValue());
                LocalDate data   = row.getCell(2).getLocalDateTimeCellValue().toLocalDate();
                String tipoSta   = row.getCell(3).getStringCellValue();

                TipoTransacao tipo = TipoTransacao.valueOf(tipoSta.toUpperCase());

                UUID contaId        = UUID.fromString(row.getCell(4).getStringCellValue());
                UUID categoriaId    = UUID.fromString(row.getCell(5).getStringCellValue());
                UUID usuarioId      = UUID.fromString(row.getCell(6).getStringCellValue());

                TransacaoRequest dto = new TransacaoRequest(
                        descricao,
                        valor,
                        tipo,
                        data,
                        contaId,
                        categoriaId,
                        usuarioId
                );

                create(dto);
                count++;

            }
        } catch (Exception e){
            throw new RuntimeException("Erro ao importar a planilha: " + e.getMessage());
        }

        return count;
    }
}
