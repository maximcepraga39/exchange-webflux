package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.ReportDto;
import md.orange.exchangeapp.entity.RapoarteZilnice;
import md.orange.exchangeapp.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public Flux<ReportDto> getReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {
        return reportService.getReportForDate(Optional.ofNullable(reportDate).orElse(LocalDate.now()))
                .map(this::convertToDto);
    }

    @PostMapping("/generate")
    public Flux<RapoarteZilnice> generateReport() {
        // only for testing. this method should be called by cron
        return reportService.generateReport();
    }

    private ReportDto convertToDto(RapoarteZilnice rapoarteZilnice) {
        return ReportDto.builder()
                .adresaCasaDeSchimb(rapoarteZilnice.getCasaDeSchimbId().toString())
                .codValuta(rapoarteZilnice.getValutaId().toString())
                .totalTranzactii(rapoarteZilnice.getTotalTranzactii())
                .sumaPrimita(rapoarteZilnice.getSumaPrimita())
                .sumaEliberata(rapoarteZilnice.getSumaEliberata())
                .dataRaport(rapoarteZilnice.getDataRaport())
                .build();
    }
}
