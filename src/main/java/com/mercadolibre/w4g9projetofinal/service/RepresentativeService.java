package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe de serviço para Representative
 *
 * @author Marcos Sá
 */

@Service
@AllArgsConstructor
public class RepresentativeService {

    /*** Instancia de BCryptPasswordEncoder: <b>BCryptPasswordEncoder</b>.
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*** Instancia de repositório: <b>RepresentativeRepository</b>.
     */
    private RepresentativeRepository repository;


    /*** Método que retorna lista de Representatives
     *
     * @return lista representantes
     */
    public List<Representative> findAll() {
        return repository.findAll();
    }


    /*** Método que busca Representative por Id
     * @param id ID do Representative a ser retornado
     * @return representante
     */
    public Representative findById(Long id) {
        Optional<Representative> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Representante não encontrado! Por favor verifique o id."));
    }

    /*** Método que insere um Representative
     * @param obj objeto Representative a ser inserido
     * @return representante
     */
    public Representative insert(Representative obj) {
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new ExistingUserException("Username ou Email existente na base de dados, ou Armazém não existe");
        }
    }

    /*** Método que atualiza um Representative já existente
     *
     * @param newObj Objeto com informações para atualização de um Representative existente
     * @return representante
     */
    public Representative update(Representative newObj) {
        Representative obj = findById(newObj.getId());
        updateRepresentation(newObj, obj);
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        return repository.save(obj);
    }

    /*** Método deleta um Representative do Bando de dados
     *
     * @param id ID do Representative a ser deletado
     */
    public void delete(Long id) {
        Representative obj = findById(id);
        repository.delete(obj);
    }

    //Método para update de Representation
    private static void updateRepresentation(Representative obj, Representative newObj) {
        newObj.setUsername(obj.getUsername());
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
        newObj.setJob(obj.getJob());
        newObj.setWarehouse(obj.getWarehouse());
    }
}
