CREATE OR REPLACE FUNCTION insert_maintenances_details(
    p_component_id INTEGER,
    p_component_type_id INTEGER,
    p_service_id INTEGER,
    p_quantity DOUBLE PRECISION,
    p_maintenance_id INTEGER
)
RETURNS VOID AS $$
DECLARE
    remaining_quantity NUMERIC(15, 2) := p_quantity;
    current_quantity NUMERIC(15, 2);
    stock_id INTEGER;
    stock_price NUMERIC(15, 2);
    sell_price NUMERIC(15, 2);
BEGIN
    IF p_quantity <=0 THEN
        INSERT INTO maintenances_details ( component_type_id, service_id, maintenance_id)
            VALUES (
                p_component_type_id,
                p_service_id,
                p_maintenance_id
            );
    ELSE
        -- Récupérer le prix de vente (sell_price) du composant
        SELECT components.sell_price
        INTO sell_price
        FROM components
        WHERE id = p_component_id;

        -- Boucle pour gérer la logique FIFO
        FOR stock_id, current_quantity, stock_price IN
            SELECT id, (entrance - outflow) AS available_quantity, unit_price
            FROM components_stock
            WHERE component_id = p_component_id AND (entrance - outflow) > 0
            ORDER BY stock_date
        LOOP
            -- Si le stock actuel peut couvrir la quantité restante
            IF current_quantity >= remaining_quantity THEN
                -- Insérer dans maintenances_details
                INSERT INTO maintenances_details (sell_price, unit_price, quantity, component_id, component_type_id, service_id, maintenance_id)
                VALUES (
                    sell_price,
                    stock_price,
                    remaining_quantity,
                    p_component_id,
                    p_component_type_id,
                    p_service_id,
                    p_maintenance_id
                );

                -- Mettre à jour le stock
                UPDATE components_stock
                SET outflow = outflow + remaining_quantity
                WHERE id = stock_id;

                remaining_quantity := 0;

                -- Stopper la boucle
                EXIT;
            ELSE
                -- Insérer dans maintenances_details avec le stock disponible
                INSERT INTO maintenances_details (sell_price, unit_price, quantity, component_id, component_type_id, service_id, maintenance_id)
                VALUES (
                    sell_price,
                    stock_price,
                    current_quantity,
                    p_component_id,
                    p_component_type_id,
                    p_service_id,
                    p_maintenance_id
                );

                -- Mettre à jour le stock
                UPDATE components_stock
                SET outflow = outflow + current_quantity
                WHERE id = stock_id;

                -- Réduire la quantité restante
                remaining_quantity := remaining_quantity - current_quantity;
            END IF;
        END LOOP;
        IF remaining_quantity > 0 THEN
            RAISE EXCEPTION 'Insufficient stock for component ID %: missing % units, current stock = %', 
                            p_component_id, remaining_quantity, current_quantity;
        END IF;
    END IF;

    -- Si la quantité restante n'a pas pu être satisfaite
END;
$$ LANGUAGE plpgsql;

-- select insert_maintenances_details (13,2,2,1,1);