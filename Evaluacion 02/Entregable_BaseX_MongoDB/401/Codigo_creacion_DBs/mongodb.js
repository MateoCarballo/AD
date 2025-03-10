{
  "_id": "cliente1@example.com",
  "nombre": "Nombre Cliente 1",
  "email": "cliente1@example.com",
  "edad": 17,
  "direccion": "Dirección Cliente 1",
  "carrito": [
    {
      "videojuego_id": 4,
      "nombre": "EA Sports FC 25",
      "cantidad": 1,
      "precio_unitario": 69.99
    },
    {
      "videojuego_id": 1,
      "nombre": "The Legend of Zelda: Breath of the Wild",
      "cantidad": 2,
      "precio_unitario": 59.99
    }
  ],
  "compras": [
    {
      "compra_id": {
        "$oid": "67a92442905d945bce7c32d6"
      },
      "videojuegos": [
        {
          "videojuego_id": 4,
          "nombre": "EA Sports FC 25",
          "cantidad": 99,
          "precio_unitario": 69.99
        },
        {
          "videojuego_id": 8,
          "nombre": "Elden Ring",
          "cantidad": 3,
          "precio_unitario": 59.99
        }
      ],
      "total": 7108.98,
      "fecha_compra": {
        "$date": "2025-01-01T23:00:00.000Z"
      }
    }
  ]
}