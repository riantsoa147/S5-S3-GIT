-- Trigger for updating technicians' salary history
CREATE OR REPLACE FUNCTION log_technician_salary_update_or_insert() RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO technicians_salary_history (salary, history_date, technician_id)
    VALUES (NEW.salary, CURRENT_DATE, NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_insert_technician_salary
AFTER INSERT OR UPDATE OF salary ON technicians
FOR EACH ROW
EXECUTE FUNCTION log_technician_salary_update_or_insert();

-- Trigger for updating services' price history
CREATE OR REPLACE FUNCTION log_service_price_update_or_insert() RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO services_price_history (price, history_date, service_id)
    VALUES (NEW.price, CURRENT_DATE, NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_insert_service_price
AFTER INSERT OR UPDATE OF price ON services
FOR EACH ROW
EXECUTE FUNCTION log_service_price_update_or_insert();

-- Trigger for updating components' stock history
CREATE OR REPLACE FUNCTION log_component_stock_update_or_insert() RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO components_stock (entrance, outflow, stock_date, component_id)
    VALUES (NEW.stock - COALESCE(OLD.stock, 0), 0, CURRENT_DATE, NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_insert_component_stock
AFTER INSERT OR UPDATE OF stock ON components
FOR EACH ROW
EXECUTE FUNCTION log_component_stock_update_or_insert();
