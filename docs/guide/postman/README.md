# Postman
#### Admin ja cadastrado para acesso aos endpoints
login: **admin**<br>
senha: **123**
### Facilitadores Postman:
#### ->  [Link para importar Collection](https://www.getpostman.com/collections/a0598b1f6201e72b7d40) para o Postman (copiar e colar)
#### -> Download do [JSON](JSONs/Postman_endpoint.json) salvo.

JSON para utilização dos endPoint no Postman
```
{
	"info": {
		"_postman_id": "08edfa2e-ad3d-4b08-b216-172f1cf612cf",
		"name": "Projeto Integrador - Meli Produtos Frescos",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "0. Login",
			"item": [
				{
					"name": "Login",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"username\": \"querty\",\n  \"pass\": \"QQqq11##\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/login",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "Esqueceu da senha",
					"request": {
						"method": "POST",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/auth/forgot/?email=h@h.co",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"auth",
								"forgot",
								""
							],
							"query": [
								{
									"key": "email",
									"value": "h@h.co"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "1. Armazem",
			"item": [
				{
					"name": "Lista Todos Armazens",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse"
							]
						}
					},
					"response": []
				},
				{
					"name": "Consulta 1 Armazem",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Cadastra Armazem",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"8ghfghgfjhg8\",\n  \"location\": \"9hjghhjghjhg\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse"
							]
						}
					},
					"response": []
				},
				{
					"name": "Altera Armazem",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"889999\",\n  \"location\": \"99\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Deleta Armazem",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Busc Armazens por Produto",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/warehouse/byProduct/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"warehouse",
								"byProduct",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "2. Usuarios",
			"item": [
				{
					"name": "Comprador",
					"item": [
						{
							"name": "Lista Todos Compradores",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/buyer",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"buyer"
									]
								}
							},
							"response": []
						},
						{
							"name": "Consultar 1 Comprador",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/buyer/3",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"buyer",
										"3"
									]
								}
							},
							"response": []
						},
						{
							"name": "Cadastrar Comprador",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"aaaa\",\n  \"name\": \"jhgh hg o\",\n  \"email\": \"hll@h.co\",\n  \"pass\": \"QQqq11##\",\n  \"address\": \"hjghhgjhghjhghjhghhhj\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/buyer",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"buyer"
									]
								}
							},
							"response": []
						},
						{
							"name": "Atualizar Comprador",
							"request": {
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"a7707\",\n  \"name\": \"jhghjhhj\",\n  \"email\": \"zsd@hjfghjgh.hjd\",\n  \"pass\": \"hfgd543FD#\",\n  \"address\": \"8888\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/buyer/21",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"buyer",
										"21"
									]
								}
							},
							"response": []
						},
						{
							"name": "Deletar Comprador",
							"request": {
								"method": "DELETE",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/buyer/21",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"buyer",
										"21"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Vendedor",
					"item": [
						{
							"name": "Listar Todos Vendedores",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/seller",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"seller"
									]
								}
							},
							"response": []
						},
						{
							"name": "Consultar 1 Vendedor",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/seller/4",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"seller",
										"4"
									]
								}
							},
							"response": []
						},
						{
							"name": "Cadastrar Vendedor",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"a87607876\",\n  \"name\": \"hfgfhgg\",\n  \"email\": \"hj0jg@ggfh.jgh\",\n  \"pass\": \"jhgh#4Djghhj\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/seller",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"seller"
									]
								}
							},
							"response": []
						},
						{
							"name": "Atualizar Vendedor",
							"request": {
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"a87607876\",\n  \"name\": \"hfgfhgg\",\n  \"email\": \"hj0jg@ggfh.jgh\",\n  \"pass\": \"jhgh#4Djghhj\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/seller/22",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"seller",
										"22"
									]
								}
							},
							"response": []
						},
						{
							"name": "Deletar Vendedo",
							"request": {
								"method": "DELETE",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/seller/22",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"seller",
										"22"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Representante",
					"item": [
						{
							"name": "Lista Todos Representantes",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/representative/",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"representative",
										""
									]
								}
							},
							"response": []
						},
						{
							"name": "Consulta 1 Representante",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/representative/7",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"representative",
										"7"
									]
								}
							},
							"response": []
						},
						{
							"name": "Cadastra Representante",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"qwpp99ertyo\",\n  \"name\": \"jhghhjgghg\",\n  \"email\": \"gfjpp9jgj@ghfg.hj\",\n  \"pass\": \"QQqq11##\",\n  \"job\": \"SUPERVISOR\",\n  \"warehouse_id\": 1\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/representative/",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"representative",
										""
									]
								}
							},
							"response": []
						},
						{
							"name": "Altera Representante",
							"request": {
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"qwpp99ertyo\",\n  \"name\": \"jhghhjgghg\",\n  \"email\": \"gfjpp9jgj@ghfg.hj\",\n  \"pass\": \"QQqq11##\",\n  \"job\": \"SUPERVISOR\",\n  \"warehouse_id\": 1\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/representative/13",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"representative",
										"13"
									]
								}
							},
							"response": []
						},
						{
							"name": "Deleta Representante",
							"request": {
								"method": "DELETE",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/api/v1/fresh-products/representative/23",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"v1",
										"fresh-products",
										"representative",
										"23"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Listar Todos Usuarios",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/users",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"users"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "3. Setor",
			"item": [
				{
					"name": "Listar Todos Setores",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/section",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"section"
							]
						}
					},
					"response": []
				},
				{
					"name": "Consultar 1 Setor",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/section/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"section",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Cadastrar Setor",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"jjjjjjjjjjjjjjtt\",\n  \"type\": \"FRESH\",\n  \"id_warehouse\": 1,\n  \"stock_limit\": 1,\n  \"current_stock\": 0,\n  \"min_teperature\": -10.0,\n  \"max_teperature\": 10.0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/section",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"section"
							]
						}
					},
					"response": []
				},
				{
					"name": "Atualizar Setor",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"jhgjhgh\",\n  \"type\": \"FRESH\",\n  \"id_warehouse\": 1,\n  \"stock_limit\": 19,\n  \"current_stock\": 0,\n  \"min_teperature\": 1.0,\n  \"max_teperature\": 1.0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/section/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"section",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Deletar Setor",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/section/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"section",
								"2"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "4. Produto",
			"item": [
				{
					"name": "Listar Todos Produtos",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product"
							]
						}
					},
					"response": []
				},
				{
					"name": "Consultar 1 Produto",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Lista Produtos Por Categoria (Refrigeração)",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/listCategory/FRESH",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"listCategory",
								"FRESH"
							]
						}
					},
					"response": []
				},
				{
					"name": "Lista Lotes Pro Produto",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/listBatch/?id=1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"listBatch",
								""
							],
							"query": [
								{
									"key": "id",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Lista Lotes Por Produtos - Ordenado",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/listBatch/C?id=1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"listBatch",
								"C"
							],
							"query": [
								{
									"key": "id",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Cria Produto base para anuncio",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"khjkhj\",\n  \"description\": \"kkkkkk\",\n  \"min_temperature\": 70.0,\n  \"max_temperature\": 90.0,\n  \"category_refrigeration\": \"FRESH\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product"
							]
						}
					},
					"response": []
				},
				{
					"name": "Atualiza Produto",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"khjkhj\",\n  \"description\": \"999999999\",\n  \"min_temperature\": 70.0,\n  \"max_temperature\": 90.0,\n  \"category_refrigeration\": \"FRESH\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/4",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"4"
							]
						}
					},
					"response": []
				},
				{
					"name": "Deleta Produto",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/product/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"product",
								"2"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "5. Anuncio",
			"item": [
				{
					"name": "Listar Todos Annuncios",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/advertise",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"advertise"
							]
						}
					},
					"response": []
				},
				{
					"name": "Consultar 1 Anuncio",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/advertise/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"advertise",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Cadastrar Anuncio",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"description\": \"hjhghhhj\",\n  \"product_id\": 1,\n  \"seller_id\": 16,\n  \"price\": 0.01,\n  \"status\": \"FINALIZADO\",\n  \"free_shipping\": true\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/advertise",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"advertise"
							]
						}
					},
					"response": []
				},
				{
					"name": "Atualizar Anuncio",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"description\": \"hjhghhhj\",\n  \"product_id\": 1,\n  \"seller_id\": 16,\n  \"price\": 10.0,\n  \"status\": \"ATIVO\",\n  \"free_shipping\": false\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/advertise/7",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"advertise",
								"7"
							]
						}
					},
					"response": []
				},
				{
					"name": "Deletar Anuncio",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/advertise/6",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"advertise",
								"6"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "6. Ordem de Entrada",
			"item": [
				{
					"name": "Inclui Ordem de Entrada",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"order_number\": 1,\n  \"order_date\": \"2022-01-28\",\n  \"section\": {\n    \"section_code\": \"1\",\n    \"warehouse_code\": \"1\"\n  },\n  \"batch_stock\": [\n    {\n      \"batch_number\": 1,\n      \"advertise_id\": 2,\n      \"current_temperature\": 89.0,\n      \"minimum_temperature\": 1.0,\n      \"initial_quantity\": 1,\n      \"current_quantity\": 1,\n      \"manufacturing_date\": \"2022-01-28\",\n      \"manufacturing_time\": \"2022-01-28T08:28:56.775775\",\n      \"due_date\": \"2022-02-28\"\n    }\n  ]\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/inboundorder",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"inboundorder"
							]
						}
					},
					"response": []
				},
				{
					"name": "Altera Ordem de Entrad",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"order_number\": 1,\n  \"order_date\": \"2022-01-28\",\n  \"section\": {\n    \"section_code\": \"1\",\n    \"warehouse_code\": \"1\"\n  },\n  \"batch_stock\": [\n    {\n      \"batch_number\": 6,\n      \"advertise_id\": 2,\n      \"current_temperature\": 89.0,\n      \"minimum_temperature\": 1.0,\n      \"initial_quantity\": 1,\n      \"current_quantity\": 2,\n      \"manufacturing_date\": \"2022-01-28\",\n      \"manufacturing_time\": \"2022-01-28T08:28:56.775775\",\n      \"due_date\": \"2022-02-28\"\n    },\n\n    {\n      \"batch_number\": 2,\n      \"advertise_id\": 2,\n      \"current_temperature\": 89.0,\n      \"minimum_temperature\": 1.0,\n      \"initial_quantity\": 1,\n      \"current_quantity\": 1,\n      \"manufacturing_date\": \"2022-01-28\",\n      \"manufacturing_time\": \"2022-01-28T08:28:56.775775\",\n      \"due_date\": \"2022-02-28\"\n    }\n  ]\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/inboundorder",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"inboundorder"
							]
						}
					},
					"response": []
				},
				{
					"name": "Lista Todas Ordens de Entrada",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/inboundorder",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"inboundorder"
							]
						}
					},
					"response": []
				},
				{
					"name": "Consulta 1 Ordem de Entrada",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/inboundorder/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"inboundorder",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "7. Controle de Validade",
			"item": [
				{
					"name": "Produtos - Validade Por Setor",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/due-date/bySection/130?sectionId=1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"due-date",
								"bySection",
								"130"
							],
							"query": [
								{
									"key": "sectionId",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Produtos - Validade Por Refrigeração - Ordenado",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/due-date/byRefrigeration/130?refrigerationType=FS&orderBy=desc",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"due-date",
								"byRefrigeration",
								"130"
							],
							"query": [
								{
									"key": "refrigerationType",
									"value": "FS"
								},
								{
									"key": "orderBy",
									"value": "desc"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "8. Carrinho de Compras",
			"item": [
				{
					"name": "Inclui Produtos do Anuncio no Carrinho",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/cart/addProduct/1?idAdvertise=2&qtdProduct=1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"cart",
								"addProduct",
								"1"
							],
							"query": [
								{
									"key": "idAdvertise",
									"value": "2"
								},
								{
									"key": "qtdProduct",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Remove Produtos do Anuncio no Carrinho",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/cart/removeProduct/1?idAdvertise=2&qtdRemove=1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"cart",
								"removeProduct",
								"1"
							],
							"query": [
								{
									"key": "idAdvertise",
									"value": "2"
								},
								{
									"key": "qtdRemove",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Esvazia o Carrinho",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/cart/emptyCart/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"cart",
								"emptyCart",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Mostra o Carrinho",
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "",
								"value": "",
								"type": "text",
								"disabled": true
							}
						],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/cart/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"cart",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Cria Ordem de Compra",
					"request": {
						"method": "PUT",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/cart/createSellOrder/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"cart",
								"createSellOrder",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "9. Delivery",
			"item": [
				{
					"name": "Cadastrar",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"hh\",\n  \"car_model\": \"h8\",\n  \"car_plate\": \"AAA0000\",\n  \"fresh_max_quantity\": 20,\n  \"frozen_max_quantity\": 10,\n  \"cold_max_quantity\": 30\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery"
							]
						}
					},
					"response": []
				},
				{
					"name": "Alterar",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"hhhhhh\",\n  \"car_model\": \"h8888\",\n  \"car_plate\": \"III0I00\",\n  \"fresh_max_quantity\": 30,\n  \"frozen_max_quantity\": 50,\n  \"cold_max_quantity\": 10\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Excluir",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "buscar todos",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "buscar por disponibilidade",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/byStatus/?isInRoute=false",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"byStatus",
								""
							],
							"query": [
								{
									"key": "isInRoute",
									"value": "false"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "buscar 1",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Chama entregador para Ordens de Envio das compras",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "\n    [\n        {\n            \"id\": 2\n        },\n        {\n            \"id\": 3\n        }\n    ]\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/delivery/",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"delivery",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "Chama entregador para Ordens de Envio das compras Copy",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "\n    [\n        {\n            \"id\": 2\n        }\n    ]\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/api/v1/fresh-products/delivery/delivery/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"v1",
								"fresh-products",
								"delivery",
								"delivery",
								"2"
							]
						}
					},
					"response": []
				}
			]
		}
	],
	"auth": {
		"type": "bearer",
		"bearer": [
			{
				"key": "token",
				"value": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxdWVydHkiLCJleHAiOjE2NDQ1MDcwNzd9.aF-CkJAyn2d3KqTlrgGEm5qYFlUPC01vrLVuVZe8AKsdURiNhrfl-NDYpibW9LA3Nfp-PU-iUxRTPQ5oW41VvQ",
				"type": "string"
			}
		]
	},
	"event": [
		{
			"listen": "prerequest",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		},
		{
			"listen": "test",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		}
	]
}
```