
package Forms;

import Clases.Proceso_Usuario;
import Clases.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class frm_Usuario extends javax.swing.JFrame {

    Proceso_Usuario procesousuario;
    Usuario datosusuario;
    int clic;
    public frm_Usuario() {
        procesousuario = new Proceso_Usuario();
        initComponents();
        Cargar_txt();
        MostrarTabla();
    }
    public void Grabar_txt(){
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter("src/Archivos/Usuario.txt");
            pw = new PrintWriter(fw);
            
            for(int i = 0; i < procesousuario.CantidadRegistro();i++){
                datosusuario = procesousuario.ObtenerRegistro(i);
                pw.println(String.valueOf(datosusuario.getNombre()+","+datosusuario.getApellido()+","+datosusuario.getPlaca()+","+
                        datosusuario.getMarca() + "," + datosusuario.getModelo() + "," + datosusuario.getAño() + "," + 
                        datosusuario.getColor()));
            }
            pw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    public void Cargar_txt(){
        File ruta = new File("src/Archivos/Usuario.txt");
        try{
            FileReader fi = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fi);
            
            String linea = null;
            
            while((linea = br.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea,",");
                datosusuario = new Usuario();
                datosusuario.setNombre(st.nextToken());
                datosusuario.setApellido(st.nextToken());
                datosusuario.setPlaca(st.nextToken());
                datosusuario.setMarca(st.nextToken());
                datosusuario.setModelo(st.nextToken());
                datosusuario.setAño(st.nextToken());
                datosusuario.setColor(st.nextToken());
               
                procesousuario.AgregarRegistro(datosusuario);
            }
            br.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
        }
    }
    
     public void Agregar(){
        try{
              if(leerNombre().equals("")) mensaje("Ingrese Nombre");
              else if(leerApellido().equals("")) mensaje("Ingrese Apellido");
              else if(leerPlaca().equals("")) mensaje("Ingrese Placa");
              else if(leerMarca().equals("")) mensaje("Ingrese Marca");
              else if(leerModelo().equals("")) mensaje("Ingrese Modelo");
              else if(leerAño().equals("")) mensaje("Ingrese Año");
              else if(leerColor().equals("")) mensaje("Ingrese Color");
              
              
              else{
                  //int dni = procesousuario.BuscarDni(leerDNI());
                  datosusuario = new Usuario(leerNombre(), leerApellido(), leerPlaca() , leerMarca(),leerModelo(),leerAño(),leerColor());
                 // if(dni!=-1 )  mensaje("USUARIO YA EXISTE");
              
                  
                       procesousuario.AgregarRegistro(datosusuario);
                       Grabar_txt();
                       MostrarTabla();
                       Reiniciar();
                  
              }
                  
                 
        }
        catch(Exception ex){
            mensaje(ex.getMessage() + "HI");  
        }
    }
    
    public void MostrarTabla(){
        DefaultTableModel usuario = new DefaultTableModel(){
           @Override
           public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        usuario.addColumn("Nombre");
        usuario.addColumn("Apellido");
        usuario.addColumn("Placa");
        usuario.addColumn("Marca");
        usuario.addColumn("Modelo");
        usuario.addColumn("Año");
        usuario.addColumn("Color");
        
        Object fila[] = new Object[usuario.getColumnCount()];
        for(int i=0;i<=procesousuario.CantidadRegistro()-1;i++){
            datosusuario = procesousuario.ObtenerRegistro(i);
            fila[0] = datosusuario.getNombre();
            fila[1] = datosusuario.getApellido();
            fila[2] = datosusuario.getPlaca();
            fila[3] = datosusuario.getMarca();
            fila[4] = datosusuario.getModelo();
            fila[5] = datosusuario.getAño();
            fila[6] = datosusuario.getColor();
            usuario.addRow(fila);
           
        }
        tblUsuario.setModel(usuario);
        tblUsuario.setRowHeight(20);
       
      this.txtCantidad.setText(""+usuario.getRowCount());  
      this.txtCupoD.setText(""+(Integer.parseInt(txtCupo.getText())-usuario.getRowCount()));
    
    
    }
    
     public void mensaje(String nota){
        JOptionPane.showMessageDialog(null, nota);
    }
   
    
   
    public String leerNombre(){
        try{
            String contra = txtNombre.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return "";
        }
    }
     public String leerApellido(){
        try{
            String contra = txtApellido.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return "";
        }
    }
     public String leerPlaca(){
        try{
           String contra = txtPlaca.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return null;
        }
    }
    public String leerMarca(){
      try{
           String contra = txtMarca.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return null;
        }
      }
    
     public String leerModelo(){
      try{
           String contra = txtModelo.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return null;
        }
      }
      public String leerAño(){
      try{
           String contra = txtColor.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return null;
        }
      }
       public String leerColor(){
      try{
           String contra = txtAño.getText().trim();
            return contra;
        }
        catch(Exception ex){
            return null;
        }
      }
  
      public void Buscar(){
          int dni = procesousuario.BuscarPlaca(txtPlaca.getText());
          if(dni!=-1){
              Eliminar();
              DefaultTableModel tabla = new DefaultTableModel(){
                   @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
           };
          
          
          tabla.addColumn("Nombres");
          tabla.addColumn("Apellidos");
          tabla.addColumn("Placa");
          tabla.addColumn("Marca");
          tabla.addColumn("Modelo");
          tabla.addColumn("Año");
          tabla.addColumn("Color");
          
              
          Object fila[] = new Object[tabla.getColumnCount()];
          fila[0] = procesousuario.Buscar_Nombre();
          fila[1] = procesousuario.Buscar_Apellido();
          fila[2] = procesousuario.Buscar_Placa();
          fila[3] = procesousuario.Buscar_Marca();
          fila[4] = procesousuario.Buscar_Modelo();
          fila[5] = procesousuario.Buscar_Año();
          fila[6] = procesousuario.Buscar_Color();
          tabla.addRow(fila);
          tblUsuario.setModel(tabla);
          tblUsuario.setRowHeight(20);
          Reiniciar();
      }
          else{
          mensaje("Ingrese Dni");
          MostrarTabla();
          Reiniciar();
          }
      }
      
      public void Eliminar(){
          DefaultTableModel tb = (DefaultTableModel) tblUsuario.getModel();
          int a = tb.getRowCount()-1;
          for(int i = a;i>=0;i--){
              tb.removeRow(tb.getRowCount()-1);
          }
      }
      public void Reiniciar(){
          txtNombre.setText("");
          txtApellido.setText("");
          txtPlaca.setText("");
          txtMarca.setText("");
          txtModelo.setText("");
          txtColor.setText("");
          txtAño.setText("");
 
          txtNombre.requestFocus();
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtAño = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCupo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCupoD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        rbAlumno = new javax.swing.JRadioButton();
        rbProfesor = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Apellido:");

        btnBuscar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUsuario);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Placa:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Marca:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Modelo:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Año:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Color:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Cupo:");

        txtCupo.setText("20");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Disponible:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Cantidad:");

        buttonGroup1.add(rbAlumno);
        rbAlumno.setText("Alumno");

        buttonGroup1.add(rbProfesor);
        rbProfesor.setText("Profesor");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Tipo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCupo, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                            .addComponent(txtCupoD)
                                            .addComponent(txtCantidad)))
                                    .addComponent(jLabel8))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnLimpiar)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnModificar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbAlumno)
                                            .addComponent(rbProfesor))
                                        .addGap(91, 91, 91))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPlaca)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAño, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(txtModelo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCupoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rbProfesor))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbAlumno)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnAgregar)
                            .addComponent(btnLimpiar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar)
                            .addComponent(btnEliminar))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       try{
           
           if(leerNombre().equals("")) mensaje("Ingrese Nombre");
           else if(leerApellido().equals("")) mensaje("Ingrese Apellido");
           else if(leerPlaca().equals("")) mensaje("Ingrese Placa");
           else if(leerMarca().equals("")) mensaje("Ingrese Marca");
           else if(leerPlaca().equals("")) mensaje("Ingrese Modelo");
           else if(leerMarca().equals("")) mensaje("Ingrese Año");
           else if(leerMarca().equals("")) mensaje("Ingrese Color");
           else{
               int dni = procesousuario.BuscarPlaca(leerPlaca());
               datosusuario = new Usuario(leerNombre(), leerApellido(), leerPlaca() , leerMarca(),leerModelo(),leerAño(),leerColor());
               if(dni == -1 )
                   mensaje("Usuario NO Encontrado");
               else
                   procesousuario.ModificarRegistro(dni, datosusuario);
               Grabar_txt();
               MostrarTabla();
               Reiniciar();
           }
       }
          catch(Exception ex){
            mensaje(ex.getMessage());
        }
       
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       Reiniciar();
       Eliminar();
       MostrarTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try{
                 if(leerPlaca().equals("")) mensaje("Ingrese Placa");
                 else{
                     int dni = procesousuario.BuscarPlaca(leerPlaca());
                     if(dni == -1) mensaje("Dni no existe");
                 
                     int s = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este Usuario","Si/No",0);
                     if(s==0){
                     procesousuario.EliminarRegistro(dni);
                     Grabar_txt();
                     MostrarTabla();
                     Reiniciar();
                     }
                }
        }
        catch(Exception ex){
            mensaje(ex.getMessage());
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbAlumno;
    private javax.swing.JRadioButton rbProfesor;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtCupoD;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables

}
