package br.com.soc.sistema.dao.exameRealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoDao extends Dao {
	
	public void insertExameRealizado(ExameRealizadoVo exameRealizadoVo){
		StringBuilder query = new StringBuilder("INSERT INTO exameRealizado (dt_resultado, codigo_exame, Nm_exame) values (?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameRealizadoVo.getNome()); 
	        ps.setString(i++, exameRealizadoVo.getCodigoExame());
	        ps.setString(i, exameRealizadoVo.getNomeExame());
	        ps.setString(i++, exameRealizadoVo.getNomeFuncionario());
            ps.setString(i++, exameRealizadoVo.getCodFuncionario());
	        
	        ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ExameRealizadoVo> findAllExameRealizados(){
		StringBuilder query = new StringBuilder("SELECT rowid id, dt_resultado nome, codigo_exame, Nm_exame, cod_funcionario, nm_funcionario FROM exameRealizado");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameRealizadoVo vo =  null;
			List<ExameRealizadoVo> exameRealizados = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameRealizadoVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				vo.setCodigoExame(rs.getString("codigo_exame"));
				vo.setNomeExame(rs.getString("Nm_exame"));
				vo.setCodFuncionario(rs.getString("cod_funcionario"));
				vo.setNomeFuncionario(rs.getString("nm_funcionario"));
				
				exameRealizados.add(vo);
			}
			return exameRealizados;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameRealizadoVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, dt_resultado nome, codigo_exame, Nm_exame, cod_funcionario, nm_funcionario FROM exameRealizado ")
								.append("WHERE lower(dt_resultado) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> exameRealizados = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					vo.setCodigoExame(rs.getString("codigo_exame"));
					vo.setNomeExame(rs.getString("Nm_exame"));
					vo.setCodFuncionario(rs.getString("cod_funcionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					
					exameRealizados.add(vo);
				}
				return exameRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public ExameRealizadoVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, dt_resultado nome, codigo_exame, Nm_exame, cod_funcionario, nm_funcionario FROM exameRealizado ")
								.append("WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setLong(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					vo.setCodigoExame(rs.getString("codigo_exame"));
					vo.setNomeExame(rs.getString("Nm_exame"));
					vo.setCodFuncionario(rs.getString("cod_funcionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	//**********EXCLUSÃO DE EXAMES REALIZADOS**********
	
	public boolean deleteExameRealizado(int cod) {
        String query = "DELETE FROM exameRealizado WHERE rowid = ?";
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, cod);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao executar a exclusão do exame realizado.", e);
        }
    }
		
	public boolean excluindoExameRealizado(String rowid) {
		
		int cod;
		if (rowid == null || rowid.isEmpty()) {
	        return false; 
	    }
	    try {
	        cod = Integer.parseInt(rowid);
	    } catch (NumberFormatException e) {
	        return false;  
	    }
	    return deleteExameRealizado(cod);  
	}
	
	
	//**********EDIÇÃO DE EXAMES REALIZADOS **********

	public void updateExameRealizado(ExameRealizadoVo exameRealizadoVo) {
	    String query = "UPDATE exameRealizado SET dt_resultado = ?, cod_exame = ?, cod_funcionario = ? WHERE rowid = ?";
	    int i = 1;
	    try (Connection con = getConexao();
	         PreparedStatement ps = con.prepareStatement(query)) {
	    	 ps.setString(i++, exameRealizadoVo.getDataResultado());
		     ps.setString(i++, exameRealizadoVo.getCodigoExame());
		     ps.setString(i++, exameRealizadoVo.getCodFuncionario());
		     ps.setLong(i++, Long.parseLong(exameRealizadoVo.getRowid()));
		     ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao atualizar o exame realizado.", e);
	    }
	}
	
}