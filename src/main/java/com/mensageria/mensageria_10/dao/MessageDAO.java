package com.mensageria.mensageria_10.dao;

import com.mensageria.mensageria_10.config.ConnectionFactory;
import com.mensageria.mensageria_10.model.Message;

import java.sql.*;
import java.util.Optional;

public class MessageDAO implements IMessageDAO {

    @Override
    public Message create(Message message) {
        String query = "INSERT INTO mensageria "+
                "(mensagem) " +
                "VALUES (?)";
        Message m = new Message();
        try (Connection con = ConnectionFactory.getConnection()){
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getMensagem());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getLong(1));
                m.setMensagem(rs.getString(2));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return m;
    }

    @Override
    public Optional<Message> read(Long id) {
        Message m = new Message();
        String query = "SELECT * FROM mensageria WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            m.setId(rs.getLong("id"));
            m.setMensagem(rs.getString("mensagem"));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.of(m);
    }
}
