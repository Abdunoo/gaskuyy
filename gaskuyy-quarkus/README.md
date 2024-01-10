## API Documentation
# Products
	get all products { 
		API = GET 'http://0.0.0.0:8282/api/products',
		example =  axios
               .get("http://localhost:8080/api/products?page=1&limit=20&search=" + search)
               .then((response) => (this.lstProducts = response.data))
               .catch((error) => console.log(error));
	}

	get product by id { 
		API = GET 'http://0.0.0.0:8282/api/products/{id}',
		example = axios
            .get("http://localhost:8080/api/products/" + id)
            .then((response) => (this.product = response.data))
            .catch((error) => console.log(error));
	}

	get product by category { 
		API = GET 'http://0.0.0.0:8282/api/products/cat/{category}'
		example = axios
               .get("http://localhost:8080/api/products/cat/" + cat + "?search=" + search)
               .then((response) => (this.lstProducts = response.data))
               .catch((error) => console.log(error));
	}

	delete product by id {
		API = DELETE 'http://0.0.0.0:8282/api/products/{id}',
		example = axios
            .delete("http://localhost:8080/api/products/" + productId)
            .then((response) => console.log('delete product success'))
            .catch((error) => console.log(error));
	}

	add or update product {
		API = PUT 'http://0.0.0.0:8282/api/products',
		example = axios.put('http://localhost:8080/api/products', selected)
            .then(response => {
               console.log('product updated successfully!');
            })
            .catch(error => {
               console.error('Error updating product:', error);
            });
	}

# Cart
	get all products on cart {
		API = GET 'http://0.0.0.0:8282/api/cart',
		example = axios
            .get("http://localhost:8080/api/cart")
            .then((response) => {
               this.lstCart = response.data;
               console.log('Cart data retrieved successfully:', this.lstCart);
            }).catch((error) => console.log(error));
	}

	get product on cart by id {
		API = GET 'http://0.0.0.0:8282/api/cart/{id}',
		example = axios.get('http://localhost:8080/api/cart/' + id)
            .then(response => {
               console.log('Cart delete successfully!');
            })
	}

	delete product on cart by id {
		API = DELETE 'http://0.0.0.0:8282/api/cart/{id}',
		example = axios.delete('http://localhost:8080/api/cart/' + id)
            .then(response => {
               console.log('Cart delete successfully!');
            })
	}

	add or update product on cart {
		API = PUT 'http://0.0.0.0:8282/api/cart',
		example = axios.put('http://localhost:8080/api/cart', selected)
            .then(response => {
               console.log('Cart updated successfully!');
            })
	}


	