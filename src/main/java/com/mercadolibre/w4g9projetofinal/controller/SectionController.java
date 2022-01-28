    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping
    @ResponseBody
    public List lista(){
        List sections = sectionRepository.findAll();
        return SectionRequestDTO.convert(sections);
    }

    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section section = sectionRequestDTO.convertToSection(warehouseRepository);
        sectionRepository.save(section);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(new SectionResponseDTO(section));
    }
