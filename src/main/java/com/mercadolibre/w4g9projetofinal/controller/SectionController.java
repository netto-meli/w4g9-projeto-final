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
