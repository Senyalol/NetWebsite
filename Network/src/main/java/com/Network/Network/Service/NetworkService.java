package com.Network.Network.Service;

import com.Network.Network.DTO.NetworkDTO.CreateNetworkDTO;
import com.Network.Network.DTO.NetworkDTO.ShortNetworkInfoDTO;
import com.Network.Network.DTO.NetworkDTO.UpdateNetworkDTO;
import com.Network.Network.Entites.Network;
import com.Network.Network.Repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NetworkService {

    private final NetworkRepository networkRepository;

    @Autowired
    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public List<ShortNetworkInfoDTO> getNetworks() {
        List<Network> networks = networkRepository.findAll();

        return networks.stream()
                .map(network -> {
                    ShortNetworkInfoDTO networkDTO = new ShortNetworkInfoDTO();
                    networkDTO.setNetwork_id(network.getId());
                    networkDTO.setNetworkName(network.getNetworkName());
                    networkDTO.setSubnet(network.getSubnet());
                    networkDTO.setGateway(network.getGateway());
                    networkDTO.setCreatedAt(network.getCreatedAt());

                    return networkDTO;
                }).toList();
    }

    public ShortNetworkInfoDTO getNetworkById(int id) {
        Network network = networkRepository.findById(id);

        ShortNetworkInfoDTO networkDTO = new ShortNetworkInfoDTO();
        networkDTO.setNetwork_id(network.getId());
        networkDTO.setNetworkName(network.getNetworkName());
        networkDTO.setSubnet(network.getSubnet());
        networkDTO.setGateway(network.getGateway());
        networkDTO.setCreatedAt(network.getCreatedAt());

        return networkDTO;
    }

    public void createNetwork(CreateNetworkDTO createNetworkDTO) {
        Network network = new Network();
        network.setNetworkName(createNetworkDTO.getNetworkName());
        network.setSubnet(createNetworkDTO.getSubnet());
        network.setGateway(createNetworkDTO.getGateway());
        network.setCreatedAt(createNetworkDTO.getCreatedAt());

        networkRepository.save(network);
    }

    public void updateNetwork(int id, UpdateNetworkDTO updateNetworkDTO) {
        Network networkToUpdate = networkRepository.findById(id);

        if (updateNetworkDTO.getNetworkName() != null) {
            networkToUpdate.setNetworkName(updateNetworkDTO.getNetworkName());
        }
        if (updateNetworkDTO.getSubnet() != null) {
            networkToUpdate.setSubnet(updateNetworkDTO.getSubnet());
        }
        if (updateNetworkDTO.getGateway() != null) {
            networkToUpdate.setGateway(updateNetworkDTO.getGateway());
        }
        if (updateNetworkDTO.getCreatedAt() != null) {
            networkToUpdate.setCreatedAt(updateNetworkDTO.getCreatedAt());
        }

        networkRepository.save(networkToUpdate);
    }

    public void deleteNetwork(int id) {
        Network networkToDelete = networkRepository.findById(id);

        networkRepository.delete(networkToDelete);
    }
}