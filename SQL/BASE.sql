CREATE TABLE brands (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE providers (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE components_type (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE services (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   price NUMERIC(15,2) NOT NULL
);

CREATE TABLE technicians (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   salary NUMERIC(15,2) NOT NULL
);

CREATE TABLE technicians_salary_history (
   id SERIAL PRIMARY KEY,
   salary NUMERIC(15,2) NOT NULL,
   history_date DATE NOT NULL,
   technician_id INTEGER NOT NULL,
   FOREIGN KEY (technician_id) REFERENCES technicians (id)
);

CREATE TABLE services_price_history (
   id SERIAL PRIMARY KEY,
   price NUMERIC(15,2) NOT NULL,
   history_date DATE NOT NULL,
   service_id INTEGER NOT NULL,
   FOREIGN KEY (service_id) REFERENCES services (id)
);

CREATE TABLE status (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE clients (
   id SERIAL PRIMARY KEY,
   email TEXT NOT NULL UNIQUE,
   phone_number VARCHAR(50),
   name VARCHAR(50) NOT NULL,
   location TEXT
);

CREATE TABLE machines_type (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE models (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   brand_id INTEGER NOT NULL,
   machine_type_id INTEGER NOT NULL,
   FOREIGN KEY (brand_id) REFERENCES brands (id),
   FOREIGN KEY (machine_type_id) REFERENCES machines_type (id)
);

CREATE TABLE dimensions (
   id SERIAL PRIMARY KEY,
   inch NUMERIC(15,2) NOT NULL,
   machine_type_id INTEGER NOT NULL,
   FOREIGN KEY (machine_type_id) REFERENCES machines_type (id)
);

CREATE TABLE components (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   sell_price NUMERIC(15,2) NOT NULL,
   stock NUMERIC(15,2) NOT NULL,
   models_id INTEGER NOT NULL,
   dimension_id INTEGER,
   component_type_id INTEGER NOT NULL,
   FOREIGN KEY (models_id) REFERENCES models (id),
   FOREIGN KEY (dimension_id) REFERENCES dimensions (id),
   FOREIGN KEY (component_type_id) REFERENCES components_type (id)
);

CREATE TABLE components_stock (
   id SERIAL PRIMARY KEY,
   entrance NUMERIC(15,2) NOT NULL,
   outflow NUMERIC(15,2) NOT NULL,
   stock_date DATE NOT NULL,
   unit_price NUMERIC(15,2) NOT NULL,
   provider_id INTEGER NOT NULL,
   component_id INTEGER NOT NULL,
   FOREIGN KEY (provider_id) REFERENCES providers (id),
   FOREIGN KEY (component_id) REFERENCES components (id)
);

CREATE TABLE components_recommandations(
   id SERIAL,
   date_recommandation DATE NOT NULL,
   component_id INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(component_id) REFERENCES components(id)
);



CREATE TABLE machines_clients_deposits (
   id SERIAL PRIMARY KEY,
   deposit_date DATE NOT NULL,
   dimension_id INTEGER NOT NULL,
   models_id INTEGER NOT NULL,
   client_id INTEGER NOT NULL,
   FOREIGN KEY (dimension_id) REFERENCES dimensions (id),
   FOREIGN KEY (models_id) REFERENCES models (id),
   FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE maintenances (
   id SERIAL PRIMARY KEY,
   price NUMERIC(15,2) NOT NULL,
   start_date TIMESTAMP NOT NULL,
   end_date TIMESTAMP,
   technician_id INTEGER NOT NULL,
   deposit_id INTEGER NOT NULL,
   status_id INTEGER NOT NULL,
   FOREIGN KEY (technician_id) REFERENCES technicians (id),
   FOREIGN KEY (deposit_id) REFERENCES machines_clients_deposits (id),
   FOREIGN KEY (status_id) REFERENCES status (id)
);

CREATE TABLE maintenances_details (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   quantity NUMERIC(15,2) NOT NULL,
   unit_price NUMERIC(15,2) NOT NULL,
   sell_price NUMERIC(15,2),
   component_type_id INTEGER NOT NULL,
   service_id INTEGER NOT NULL,
   component_id INTEGER NOT NULL,
   maintenance_id INTEGER NOT NULL,
   FOREIGN KEY (component_type_id) REFERENCES components_type (id),
   FOREIGN KEY (service_id) REFERENCES services (id),
   FOREIGN KEY (component_id) REFERENCES components (id),
   FOREIGN KEY (maintenance_id) REFERENCES maintenances (id)
);

CREATE TABLE diagnostics (
   id SERIAL PRIMARY KEY,
   diagnostics_date DATE NOT NULL,
   deposit_id INTEGER NOT NULL,
   FOREIGN KEY (deposit_id) REFERENCES machines_clients_deposits (id)
);

CREATE TABLE failing_components (
   id SERIAL PRIMARY KEY,
   service_id INTEGER NOT NULL,
   component_type_id INTEGER NOT NULL,
   diagnostic_id INTEGER NOT NULL,
   FOREIGN KEY (service_id) REFERENCES services (id),
   FOREIGN KEY (component_type_id) REFERENCES components_type (id),
   FOREIGN KEY (diagnostic_id) REFERENCES diagnostics (id)
);
