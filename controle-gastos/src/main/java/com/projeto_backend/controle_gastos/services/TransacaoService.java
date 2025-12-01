package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import com.projeto_backend.controle_gastos.dtos.TransacaoRequest;
import com.projeto_backend.controle_gastos.dtos.TransacaoResponse;
import com.projeto_backend.controle_gastos.mappers.TransacaoMapper;
import com.projeto_backend.controle_gastos.models.Transacoes;
import com.projeto_backend.controle_gastos.repositories.TransacaoRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
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

    // @Autowired
    // private ContaRepository contaRepository;

    public TransacaoResponse create(TransacaoRequest dto) {
        Transacoes transacoes= TransacaoMapper.toEntity(dto);
        Transacoes saved = repository.save(transacoes);

        // atualizarSaldo(saved, false)

        return TransacaoMapper.toResponse(saved);
    }

    public Page<TransacaoResponse> getAll(UUID usuarioId, TipoTransacao tipo, LocalDate inicio, LocalDate fim, Pageable pageable) {
        Page<Transacoes> page = repository.findByUsuarioIdAndTipoAndDataMovimetacaoBetween(usuarioId, tipo, inicio, fim, pageable);
        return page.map(TransacaoMapper::toResponse);
    }

    public TransacaoResponse getOne(UUID id) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));
        return TransacaoMapper.toResponse(t);
    }

    public TransacaoResponse update(UUID id, TransacaoRequest dto) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));

        // Transacoes antigo = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));

        // atualizarSaldo(antigo, true);

        t.setDescricao(dto.descricao());
        t.setValor(dto.valor());
        t.setDataMovimentacao(dto.dataMovimentacao());
        t.setTipo(dto.tipo());
        t.setContaId(dto.contaId());
        t.setCategoriaId(dto.categoriaId());
        t.setUsuarioId(dto.usuarioId());

        // Transacoes atualizado = repository.save(antigo);

        // atualizarSaldo(atualizado, false);

        return TransacaoMapper.toResponse(repository.save(t));
    }

    public void delete(UUID id) {
        // Transacoes t = repository.findById(id)
        //             .orElseThrow(() -> new NoSuchElementException("Transação não encontrado"));

        // atualizarSaldo(t, true)

        repository.deleteById(id);
    }

    public int importarPlanilha(MultipartFile file) {
        int count = 0;

        try (InputStream is = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(is)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter(); // ← evita erros de tipo

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Descrição (string segura)
                String descricao = formatter.formatCellValue(row.getCell(0));

                // Valor (pode vir string ou número)
                String valorStr = formatter.formatCellValue(row.getCell(1))
                        .replace("R$", "")
                        .replace(".", "")
                        .trim();

                BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));

                // Data (converter com DataFormatter também)
                String dataStr = formatter.formatCellValue(row.getCell(2));

                LocalDate data = LocalDate.parse(dataStr); // formato yyyy-MM-dd
                // Se vier dd/MM/yyyy, trocar por:
                // LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // Tipo (string normal)
                String tipoSta = formatter.formatCellValue(row.getCell(3)).toUpperCase();
                TipoTransacao tipo = TipoTransacao.valueOf(tipoSta);

                // UUIDs (planilha sempre entrega string)
                UUID contaId = UUID.fromString(formatter.formatCellValue(row.getCell(4)));
                UUID categoriaId = UUID.fromString(formatter.formatCellValue(row.getCell(5)));
                UUID usuarioId = UUID.fromString(formatter.formatCellValue(row.getCell(6)));

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

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar a planilha: " + e.getMessage());
        }

        return count;
    }


    // private void atualizarSaldo(Transacoes t, boolean revert){
    //     conta = contaRepository.findById(t.getContaId())
    //             .orElseThrow(() -> new NoSuchElementException("Conta não encontrada"))

    //     BigDecimal saldo = conta.getSaldoAtual();

    //     BigDecimal valor = t.getValor();

    //     if (revert) {
    //         if (t.getTipo() == TipoTransacao.ENTRADA) {
    //             saldo = saldo.subtract(valor)
    //         } else {
    //             saldo = saldo.add(valor)
    //         }
    //     } else {
    //         if (t.getTipo() == TipoTransacao.ENTRADA) {
    //             saldo = saldo.add(valor)
    //         } else {
    //             saldo = saldo.subtract(valor);
    //         }
    //     }

    //     conta.setSaldoAtual(saldo);
    //     contaRepository.save(conta);
    // }
}
